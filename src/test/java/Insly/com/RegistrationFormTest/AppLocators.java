package Insly.com.RegistrationFormTest;

import org.openqa.selenium.By;

public class AppLocators {
    static By PRIVACY_END = By.xpath("//h3[text()='XII. AMENDMENTS TO THE PRIVACY POLICY']");
    static By LNKPRIVACY_POLICY = By.xpath("//a[text()='privacy policy']");
    static By CLOSE_PRIVACY = By.xpath("//span[text()='Privacy Policy']/parent::div//span[@class='icon-close']") ;
    static By INPCOMPANY_NAME = By.xpath("//input[@id='broker_name']");
    static By SEL_COUNTRY = By.xpath("//select[@id='broker_address_country']");
    static By SEL_COMPANYPROFILE = By.xpath("//select[@id='prop_company_profile']");
    static By SEL_NUMOFEMPLOYE = By.xpath("//select[@id='prop_company_no_employees']");
    static By SEL_DESCRIBE = By.xpath("//select[@id='prop_company_person_description']");
    static By INP_WORKEMAIL = By.xpath("//input[@id='broker_admin_email']");
    static By INP_ACCMNGRNME = By.xpath("//input[@id='broker_admin_name']");
    static By INP_PWD = By.xpath("//input[@id='broker_person_password']");
    static By INP_PWDREPT = By.xpath("//input[@id='broker_person_password_repeat']");
    static By INP_PHN = By.xpath("//input[@id='broker_admin_phone']");
    static By CHK_TERMS = By.xpath("//input[@id='agree_termsandconditions']/parent::label//span");
    static By CHK_PRIVCY = By.xpath("//input[@id='agree_privacypolicy']/parent::label//span");
    static By CHK_PERSONAL = By.xpath("//input[@id='agree_data_processing']/parent::label//span");
    static By BTN_SIGNUP = By.xpath("//button[text()='Sign Up']");
    static By LNK_SUGGESTPWD = By.xpath("//a[text()='suggest a secure password']");
    static By TXT_SUGSTPWD = By.xpath("//div[@id='insly_alert']/b");
    static By BTN_OK = By.xpath("//button[text()='OK']");
    static By INP_INSLYADD = By.xpath("//input[@id='broker_tag']");
}

