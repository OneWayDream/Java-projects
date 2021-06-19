package ru.itis.javalab.server.services.request;

import ru.itis.javalab.server.dto.forms.SignUpForm;


public interface SignService {
    void handleSignUpForm(SignUpForm form);
}
