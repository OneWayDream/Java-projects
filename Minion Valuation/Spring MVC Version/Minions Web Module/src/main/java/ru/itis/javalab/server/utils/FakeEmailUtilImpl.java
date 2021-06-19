package ru.itis.javalab.server.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class FakeEmailUtilImpl implements EmailUtil {

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }
}
