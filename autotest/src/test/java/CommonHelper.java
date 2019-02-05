import com.github.javafaker.Faker;

public class CommonHelper {

    Faker faker = new Faker();

    public String getRandomEmail(String localPart){
        return faker.internet().emailAddress(localPart);
    }

    public String getRandomEmail(){
        return faker.internet().emailAddress();
    }

    public String getRandomPassword(int min, int max){
        return faker.internet().password(min, max);
    }

    public String getRandomName(){
        return faker.name().username();
    }

    public String getRandomPhoneNumber(){
        return faker.phoneNumber().phoneNumber();
    }
}
