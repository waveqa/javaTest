package e2e;

import org.junit.*;
import services.MainPage;

public class ClassTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
    }

    @Test
    public void createNewUser(){
        mainPage.clickSignUp();
        mainPage.fillUsername(fakerHelper.getRandomName());
        mainPage.fillPhoneNumber(fakerHelper.getRandomPhoneNumber());
        mainPage.fillEmail(fakerHelper.getRandomEmail("test123"));
        mainPage.fillPassword(fakerHelper.getRandomPassword(6,8));
        mainPage.clickCreateAccount();
    }
}