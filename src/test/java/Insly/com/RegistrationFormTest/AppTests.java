package Insly.com.RegistrationFormTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTests {
    public static WebDriver driver;
    public static ChromeOptions options;

    // ************** METHOD Definitions *************** //

    // Initialized the Chrome Driver and Launches URL
    public void init() {
        System.setProperty("webdriver.chrome.driver" , System.getProperty("user.dir") + File.separator + "chromedriver.exe");

        options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        options.addArguments("--disable-extensions" , "--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--safebrowsing-disable-download-protection");
        prefs.put("profile.default_content_settings.popups" , 0);
        prefs.put("safebrowsing.enabled" , "true");
        options.setExperimentalOption("prefs" , prefs);
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR , UnexpectedAlertBehaviour.DISMISS);
        options.setCapability("chrome.switches" , Arrays.asList("--incognito"));
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS , true);

    }

    /**
     * Thread Wait for specified timeout in seconds
     *
     * @param sec
     */
    public void wait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ep) {
            ep.printStackTrace();
        }
    }

    /**
     * Launch Input URL
     *
     * @param inpUrl
     * @throws InterruptedException
     */
    public void launchURL(String inpUrl) throws InterruptedException {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(inpUrl);
        wait(2);
    }

    /**
     * Click on Element
     * @param loc
     */
    public void clickElement(By loc) {
        try {
            wait(2);
            WebElement ele = driver.findElement(loc);
            ele.click();
            wait(2);
        } catch (AssertionError | Exception e) {
            e.printStackTrace();
            System.out.println("Failed to click on element");
        }
    }

    /***
     * Scroll INnto View of Element
     * @param inpLoc
     */
    public void scrollView(By inpLoc) {
        try {
            WebElement ele = driver.findElement(inpLoc);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" ,
                    ele );
        } catch (Exception | AssertionError e) {
            e.printStackTrace();
        }
    }

    /**
     * Set text in Input Box
     * @param inpLoc
     * @param text
     */
    public void setValue(By inpLoc,String text) {
        try {
            WebElement ele = driver.findElement(inpLoc);
            if (ele.getTagName().equalsIgnoreCase("input") || ele.getTagName().equalsIgnoreCase("textarea")) {
                ele.sendKeys(text);
                wait(2);
            }
            else if (ele.getTagName().equalsIgnoreCase("select")) {
                Select sel = new Select(ele);
                sel.selectByVisibleText(text);
                wait(2);
            } else {
                Assert.fail("Failed to Enter Value in text box");
            }
        } catch (AssertionError | Exception ep) {
            Assert.fail("Failed to find the text box");
        }
    }

    /**
     * Close and Quit Driver
     */
    private void closeDriver() {
        driver.close();
        driver.quit();
    }

    /**
     * Fill the SignUp form for Insly
     */
    public void fillForm() {
        String compName = "TestCompany"+System.currentTimeMillis();
        String phnNum = "931452189";
        phnNum = phnNum.substring(0,9);

        setValue(AppLocators.INPCOMPANY_NAME,compName);
        setValue(AppLocators.SEL_COUNTRY,"Ukraine");
        setValue(AppLocators.SEL_COMPANYPROFILE,"Insurance Agency");

        // Checking Company Name
        chkCompanyName(compName);

        setValue(AppLocators.SEL_NUMOFEMPLOYE,"6-10");
        setValue(AppLocators.SEL_DESCRIBE,"I am the CEO of the company");
        setValue(AppLocators.INP_WORKEMAIL,"test@workableemail.com");
        setValue(AppLocators.INP_ACCMNGRNME,"TestAcc MngrNme");

        String getPwd = getSecurePwd();

        setValue(AppLocators.INP_PHN,phnNum);

        clickElement(AppLocators.CHK_TERMS);
        readPrivacy();
        clickElement(AppLocators.CHK_PRIVCY);
        clickElement(AppLocators.CHK_PERSONAL);

        wait(3);
        clickElement(AppLocators.BTN_SIGNUP);
    }

    private void readPrivacy() {
        clickElement(AppLocators.LNKPRIVACY_POLICY);
        wait(3);
        scrollView(AppLocators.PRIVACY_END);
        clickElement(AppLocators.CLOSE_PRIVACY);
        wait(2);
    }

    /**
     * Get Secure Pwd
     * @return
     */
    private String getSecurePwd() {
        clickElement(AppLocators.LNK_SUGGESTPWD);
        String txt = driver.findElement(AppLocators.TXT_SUGSTPWD).getText().trim();
        clickElement(AppLocators.BTN_OK);

        return txt;
    }

    /**
     * Verifies the Company Name is auto generated
     * @param compName
     */
    private void chkCompanyName(String compName) {
        String val = driver.findElement(AppLocators.INP_INSLYADD).getAttribute("value");

        if (val.equalsIgnoreCase(compName)) {
            Assert.assertTrue(val.equalsIgnoreCase(compName),"Successfull verified Compname");
        } else {
            Assert.fail("Failed to verify Generated Email Address");
        }
    }

    @Test
    public void testForm() throws InterruptedException {
        AppTests app = new AppTests();
        app.init();
        app.launchURL("http://signup.insly.com");
        app.wait(2);

        fillForm();

        app.closeDriver();
    }
}

