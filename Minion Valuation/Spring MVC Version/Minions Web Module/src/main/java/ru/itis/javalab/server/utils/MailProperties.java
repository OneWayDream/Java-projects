package ru.itis.javalab.server.utils;

import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Data
public class MailProperties {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String protocol = "smtp";

    private Charset defaultEncoding = DEFAULT_CHARSET;

    private Map<String, String> properties = new HashMap<>();

    private String jndiName;
}
