package utils;

import com.github.javafaker.Faker;

public class DynamicDataFactory {

    private static final Faker faker = new Faker();

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomUsername() {
        return faker.name().username();
    }

    public static String randomPassword() {
        return faker.internet().password(8, 12, true, true);
    }

    public static String randomPhone() {
        return faker.phoneNumber().cellPhone();
    }
}

