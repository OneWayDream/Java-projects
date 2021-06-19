package ru.itis.javalab.server.utils;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
