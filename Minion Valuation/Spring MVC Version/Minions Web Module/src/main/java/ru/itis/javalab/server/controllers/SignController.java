package ru.itis.javalab.server.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.javalab.server.annotations.Log;
import ru.itis.javalab.server.dto.forms.SignInForm;
import ru.itis.javalab.server.dto.forms.SignUpForm;
import ru.itis.javalab.server.exceptions.SignUpException;
import ru.itis.javalab.server.services.request.SignService;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@Log
public class SignController {

    @Autowired
    protected SignService signService;

    //Hello I'm useless
    //Exists only for MvcUriComponentsBuilder
    //Logout url is handled by SpringSecurityFilterChain;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public String getLogoutPage(){
        return "redirect:" + MvcUriComponentsBuilder
                .fromMappingName("UC#getRoot").build();
    }

    //Hello I'm useless
    //Exists only for MvcUriComponentsBuilder
    //Logout url is handled by SpringSecurityFilterChain;
    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/git-oauth")
    public String getGitOauthPage(){
        return "redirect:" + MvcUriComponentsBuilder
                .fromMappingName("UC#getRoot").build();
    }


    @PreAuthorize("!isAuthenticated()")
    @GetMapping(value = "/sign-in")
    public String getSignInPage(Model model, @RequestParam(value = "error", required = false) String authError){
        model.addAttribute("signInForm", new SignInForm());

        if (authError!=null){
            model.addAttribute("error", "errors.sign_in_form.incorrect_data");
        }
        return "sign-in";
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping(value = "/sign-up")
    public String getSignUpPage(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign-up";
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping(value = "/sign-up")
    public String handleSignUp(@Valid SignUpForm signUpForm, BindingResult bindingResult, Model model,
                               RedirectAttributes attributes){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().allMatch(error -> {
                System.out.println(error);
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.ValidPasswords")){
                    model.addAttribute("message", error.getDefaultMessage());
                }
                return true;
            });
            signUpForm.setPassword("");
            signUpForm.setRepeatedPassword("");
            model.addAttribute("signUpForm", signUpForm);
            return "sign-up";
        } else {
            try{
                signService.handleSignUpForm(signUpForm);
                attributes.addFlashAttribute("/success", true);
//                return "redirect:/success";
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#handleSuccessAction").build();
            } catch (SignUpException ex){
                signUpForm.setPassword("");
                signUpForm.setRepeatedPassword("");
                model.addAttribute("signUpForm", signUpForm);
                model.addAttribute("spring_message", ex.getMessage());
                return "sign-up";
            }
        }
    }
}
