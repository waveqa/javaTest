package services;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private String signUpForm = "[data-id=\"signup\"]";
    private SelenideElement txtUsername = $(byName("username"));
    private SelenideElement txtPass = $(byName("password"));
    private SelenideElement btnLogin = $("[id=\"signin-form\"] .button_success");
    private SelenideElement btnVerifyRecaptcha = $("[title=\"проверка recaptcha\"]");
    private ElementsCollection btnHidePostByIndex = $$(".collapse-button");
    private SelenideElement btnNew = $("[href=\"/new\"]");
    private SelenideElement btnSignup = $("[data-to=\"signup\"]");
    private SelenideElement txtSignupUsername = $(signUpForm + " [name=\"username\"]");
    private SelenideElement txtPhoneNumber = $(signUpForm + " [name=\"phone\"]");
    private SelenideElement txtEmail = $(signUpForm + " [name=\"email\"]");
    private SelenideElement btnCreateAccount = $(signUpForm + " [type=\"submit\"]");
    private SelenideElement txtSignUpPassword = $(signUpForm + " [name=\"password\"]");

    public void setUsername(String value){
        txtUsername.setValue(value);
    }

    public void setPassword(String value){
        txtPass.setValue(value);
    }

    public SelenideElement getVerifyRecaptchaButton() {
        return btnVerifyRecaptcha;
    }

    public void clickLogin(){
        btnLogin.click();
    }

    public void clickNewPosts(){
        btnNew.click();
    }

    public void clickHidePostByIndex(int index){
        btnHidePostByIndex.get(index).click();
    }

    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        clickLogin();
    }

    public void fillUsername(String value) {
        txtSignupUsername.setValue(value);
    }

    public void fillPhoneNumber(String value) {
        txtPhoneNumber.setValue(value);
    }

    public void fillEmail(String value) {
        txtEmail.setValue(value);
    }

    public void fillPassword(String value) {
        txtSignUpPassword.setValue(value);
    }

    public void clickCreateAccount() {
        btnCreateAccount.click();
    }

    public void clickSignUp() {
        btnSignup.click();
    }
}