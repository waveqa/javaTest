package services;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement signUpForm = $("[data-id=\"signup\"]");
    private SelenideElement txtUsername = $(byName("username"));
    private SelenideElement txtPass = $(byName("password"));
    private SelenideElement btnLogin = $("[id=\"signin-form\"] .button_success");
    private SelenideElement btnVerifyRecaptcha = $("#recaptcha-verify-button");
    private ElementsCollection btnHidePostByIndex = $$(".collapse-button");
    private SelenideElement btnNew = $("[href=\"/new\"]");
    private SelenideElement btnSignup = $("[data-to=\"signup\"]");
    private SelenideElement txtSignupUsername = signUpForm.$("[name=\"username\"]");
    private SelenideElement txtPhoneNumber = signUpForm.$("[name=\"phone\"]");
    private SelenideElement txtEmail = signUpForm.$("[name=\"email\"]");
    private SelenideElement btnCreateAccount = signUpForm.$("[type=\"submit\"]");
    private SelenideElement txtSignUpPassword = signUpForm.$("[name=\"password\"]");
    private SelenideElement captchaFrame = $("[title=\"recaptcha challenge\"]");

    public SelenideElement getTxtSignUpPassword() {
        return txtSignUpPassword;
    }

    public void setUsername(String value){
        txtUsername.setValue(value);
    }

    public void setPassword(String value){
        txtPass.setValue(value);
    }

    public void getVerifyRecaptchaButton() {
        captchaFrame.shouldBe(Condition.visible);
        switchTo().frame(captchaFrame);
        btnVerifyRecaptcha.shouldBe(Condition.visible);
        btnVerifyRecaptcha.pressEscape();
        switchTo().defaultContent();
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