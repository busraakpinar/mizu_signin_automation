package com.ciceksepeti.mizu;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SigninTest {

    WebDriver driver = null;

    private WebElement assertWaitVisible(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            fail();
            return null;
        }
    }

    private void assertWaitRedirect(String url_contains){
  
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.urlContains(url_contains));
        } catch (Exception e) {
            fail();
        }
    }

    private void assertWaitNotVisible(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            fail();
        } catch (Exception e) {}
    }

    @BeforeEach
    public void testCaseSetup() {
       
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        //driver.manage().window().maximize();
        driver.get("https://www.mizu.com/en-mx/login");
    }

    @AfterEach
    public void testCaseTeardown() {
        driver.quit();
    }

    // Scenario: The correct user name and password are given to the login page, and the login button is clicked.
    // Expectation: Login should be successful.
    @Test
    public void TC01LoginValidUsernameAndValidPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("set your valid e-mail");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("set your valid password");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // Message that is going to be displayed when the input is unable to be handled
        assertWaitNotVisible(By.xpath("//*[contains(text(), 'E-mail address or password is incorrect. Please check your information and try again.')]"));

        // The "Login" of the URL includes the "Login" input page and is provided to the test of the test with assertfalse
        assertFalse(driver.getCurrentUrl().contains("login"));

        // The end of the URL address must be '/ account / edit-information' when logged in. 
        // * The sign in the URL received with CSSSelector was used to prevent the entry control to be affected by the use of the site in different languages.
        WebElement loginSuccessCheck = driver.findElement(By.cssSelector("a[href*='/account/edit-information']"));
        assertTrue(loginSuccessCheck != null);

    }

    // Scenario: The correct user name and incorrect password are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC02LoginValidUsernameAndInvalidPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("set your valid e-mail");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("Invalidpswd.");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        // The Pop-up is expected to be displayed with the negative input and the relevant message is not received, the test is provided in the catch blog
        assertWaitVisible(By.xpath("//*[contains(text(), 'E-mail address or password is incorrect. Please check your information and try again.')]"));
     
    }

    // Scenario: The incorrect user name and correct password are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC03LoginInvalidUsernameAndValidPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("invalidemail@gmail.com");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("set your valid password");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        // The Pop-up is expected to be displayed with the negative input and the relevant message is not received, the test is provided in the catch blog
        assertWaitVisible(By.xpath("//*[contains(text(), 'E-mail address or password is incorrect. Please check your information and try again.')]"));

    }

    // Scenario: The incorrect user name and password are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC04LoginInvalidUsernameAndInvalidPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("invalidemail@gmail.com");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("Invalidpswd.");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        // The Pop-up is expected to be displayed with the negative input and the relevant message is not received, the test is provided in the catch blog
        assertWaitVisible(By.xpath("//*[contains(text(), 'E-mail address or password is incorrect. Please check your information and try again.')]"));

    }

    // Scenario: The blank user name are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC05LoginBlankUsername() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("set your valid password");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        assertWaitVisible(By.xpath("//span[contains(text(), 'Required field.')]"));

    }

    // Scenario: The blank password are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC06LoginBlankPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("set your valid e-mail");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        assertWaitVisible(By.xpath("//span[contains(text(), 'Required field.')]"));

    }

    // Scenario: The blank user name and password are given to the login page, and the login button is clicked.
    // Expectation: Login should be unsuccessful.
    @Test
    public void TC07LoginBlankUsernameAndLoginBlankPassword() {

        WebElement setEmailAdress = driver.findElement(By.xpath("//input[@id='EmailLogin']"));
        setEmailAdress.click();
        setEmailAdress.sendKeys("");

        WebElement setPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        setPassword.click();
        setPassword.sendKeys("");

        WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signinButton.click();

        // If the URL address contains "Login", it means that it is on the input screen, and the test is ensured by assertTrue
        assertTrue(driver.getCurrentUrl().contains("login"));

        assertWaitVisible(By.xpath("//span[contains(text(), 'Required field.')]"));

    }

    // Scenario: Click the "Login with Gmail" button.
    // Expectation: Login should be successful
    @Test
    public void TC08LoginWithFacebook() throws InterruptedException  {

        WebElement loginWithFacebookButton = driver.findElement(By.xpath("//span[contains(text(), 'Sign in with Facebook') and contains(@class, 'login__social-text')]"));
        loginWithFacebookButton.click();
        
  
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;


        while(driver.getWindowHandles().size() == 1);
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
            System.out.println(subWindowHandler);
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
                
        WebElement setEmailForFacebook = driver.findElement(By.id("email"));
        setEmailForFacebook.click();
        setEmailForFacebook.sendKeys("set your valid e-mail");

        WebElement setPasswordForFacebook = driver.findElement(By.id("pass"));
        setPasswordForFacebook.click();
        setPasswordForFacebook.sendKeys("set your valid password");

        WebElement facebookLoginButton = driver.findElement(By.id("loginbutton"));
        facebookLoginButton.click();

        driver.switchTo().window(parentWindowHandler);  // switch back to parent window

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        // The "Login" of the URL includes the "Login" input page and is provided to the test of the test with assertfalse
        assertFalse(driver.getCurrentUrl().contains("login"));

        // The end of the URL address must be '/ account / edit-information' when logged in. 
        // * The sign in the URL received with CSSSelector was used to prevent the entry control to be affected by the use of the site in different languages.
        WebElement loginSuccessCheck = driver.findElement(By.cssSelector("a[href*='/account/edit-information']"));
        assertTrue(loginSuccessCheck != null);


    }

    // Scenario: Click the "Login with Gmail" button.
    // Expectation: Login should be successful
    @Test
    public void TC09LoginWithGmail() throws InterruptedException {
        
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        
        WebElement loginWithGmailButton = driver.findElement(By.xpath("//span[contains(text(), 'Sign in with Google') and contains(@class, 'login__social-text login__social-google-text')]"));
        loginWithGmailButton.click();
        System.out.println(driver.getCurrentUrl());
   
        System.out.flush();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        assertWaitRedirect("google");
 
        WebElement setEmailForGmail = assertWaitVisible(By.id("identifierId"));
        setEmailForGmail.click();
        setEmailForGmail.sendKeys("clarklisa686@gmail.com");

        WebElement forwardButton = driver.findElement(By.xpath("//span[contains(text(), 'İleri') and contains(@class, 'VfPpkd-vQzf8d')]"));
        forwardButton.click();

        WebElement  setPasswordForGmail = assertWaitVisible(By.xpath("//*[contains(@type, 'password') and contains(@class, 'whsOnd zHQkBf')]"));

        // WebElement setPasswordForGmail = driver.findElement(By.xpath("//*[contains(@type, 'password') and contains(@class, 'whsOnd zHQkBf')]"));
        setPasswordForGmail.click();
        setPasswordForGmail.sendKeys("T1e2s3t4.");
        forwardButton = driver.findElement(By.xpath("//span[contains(text(), 'İleri') and contains(@class, 'VfPpkd-vQzf8d')]"));

        forwardButton.click();

        // The "Login" of the URL includes the "Login" input page and is provided to the test of the test with assertfalse
        assertFalse(driver.getCurrentUrl().contains("login"));

    }

}
