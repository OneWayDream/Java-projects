package ru.itis.javalab.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.javalab.server.annotations.Log;
import ru.itis.javalab.server.dto.*;
import ru.itis.javalab.server.dto.forms.AccountDeleteForm;
import ru.itis.javalab.server.dto.forms.PasswordEditForm;
import ru.itis.javalab.server.dto.forms.ProfileEditForm;
import ru.itis.javalab.server.exceptions.IncorrectConfirmCodeException;
import ru.itis.javalab.server.exceptions.IncorrectPasswordException;
import ru.itis.javalab.server.exceptions.UsersRepositoryException;
import ru.itis.javalab.server.models.User;
import ru.itis.javalab.server.security.authentications.GithubAuthentication;
import ru.itis.javalab.server.security.details.UserDetailsImpl;
import ru.itis.javalab.server.services.request.ProfileEditService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
@Log
public class ProfileController {

    @Autowired
    protected ProfileEditService profileEditService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("user", userDetails.getUser());
        return "profile";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile-edit")
    public String getProfileEditPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        addProfileEditPageAttributes(model, userDetails);
        return "profile-edit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile-data-edit")
    public String handleProfileEdit(@Valid ProfileEditForm profileEditForm, BindingResult bindingResult, Model model,
                                    RedirectAttributes attributes, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (bindingResult.hasErrors()){
            model.addAttribute("profileEditForm", profileEditForm);
            addProfileEditPageAttributes(model, userDetails);
            return "profile-edit";
        } else {
            try{
                UserDto updatedUser = profileEditService.handleProfileEditForm(userDetails.getUser(), profileEditForm);
                resetAuthentication(updatedUser);
                attributes.addFlashAttribute("message", "profile_edit_page.success_data_edit_message");
//                return "redirect:/profile";
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfilePage").build();
            } catch (UsersRepositoryException ex){
                model.addAttribute("profileEditForm", profileEditForm);
                addProfileEditPageAttributes(model, userDetails);
                model.addAttribute("data_message", "errors.profile_data_edit_form.repository_exception");
                return "profile-edit";
            }
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile-password-edit")
    public String handleProfilePasswordEdit(@Valid PasswordEditForm passwordEditForm, BindingResult bindingResult, Model model,
                                            RedirectAttributes attributes, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().allMatch(error -> {
                System.out.println(error);
                if (Objects.requireNonNull(error.getCodes())[0].equals("passwordEditForm.ValidPasswords")){
                    model.addAttribute("password_message", error.getDefaultMessage());
                }
                return true;
            });
            addProfileEditPageAttributes(model, userDetails);
            return "profile-edit";
        } else {
            try{
                UserDto updatedUser = profileEditService.handlePasswordEditForm(userDetails.getUser(), passwordEditForm);
                resetAuthentication(updatedUser);
                attributes.addFlashAttribute("message", "profile_edit_page.success_password_edit_message");
//                return "redirect:/profile";
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfilePage").build();
            } catch (UsersRepositoryException ex){
                addProfileEditPageAttributes(model, userDetails);
                model.addAttribute("data_message", "errors.profile_password_edit_form.repository_exception");
                return "profile-edit";
            } catch (IncorrectPasswordException ex){
                addProfileEditPageAttributes(model, userDetails);
                model.addAttribute("password_message", "errors.password_edit_form.wrong_password");
                return "profile-edit";
            }
        }
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile-delete")
    public String handleProfileDelete(@Valid AccountDeleteForm accountDeleteForm, BindingResult bindingResult,
                                      Model model,
                                      RedirectAttributes attributes,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (bindingResult.hasErrors()){
            addProfileEditPageAttributes(model, userDetails);
            return "profile-edit";
        } else {
            try{
                profileEditService.handleAccountDeleteForm(userDetails.getUser(), accountDeleteForm);
                return "redirect:" + MvcUriComponentsBuilder
                        .fromMappingName("SC#getLogoutPage").build();
            } catch (UsersRepositoryException ex){
                addProfileEditPageAttributes(model, userDetails);
                model.addAttribute("delete_message", "errors.profile_account_delete_form.repository_exception");
                return "profile-edit";
            } catch (IncorrectConfirmCodeException ex){
                addProfileEditPageAttributes(model, userDetails);
                model.addAttribute("delete_message", "errors.profile_account_delete_form.incorrect_confirm_string");
                return "profile-edit";
            }
        }
    }

    protected void resetAuthentication(UserDto newUserData){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof GithubAuthentication){
            GithubAuthentication newAuthentication = new GithubAuthentication(null);
            newAuthentication.setAuthenticated(true);
            newAuthentication.setUserDetails(new UserDetailsImpl(newUserData));
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
        else if (authentication instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken currentAuthentication = (UsernamePasswordAuthenticationToken) authentication;
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    new UserDetailsImpl(newUserData), currentAuthentication.getCredentials(), currentAuthentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        else if (authentication instanceof RememberMeAuthenticationToken){
            try{
                RememberMeAuthenticationToken currentAuthentication = (RememberMeAuthenticationToken) authentication;
                RememberMeAuthenticationToken authenticationToken = RememberMeAuthenticationToken
                        .class
                        .getDeclaredConstructor(Integer.class, Object.class, Collection.class)
                        .newInstance(
                                currentAuthentication.getKeyHash()
                                , new UserDetailsImpl(newUserData)
                                , currentAuthentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                //ignore
            }

        }
    }

    protected void addProfileEditPageAttributes(Model model, UserDetailsImpl userDetails){
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("genders", Arrays.stream(User.Gender.values()).map(GenderDto::from).collect(Collectors.toList()));
        addForms(model, userDetails);
    }

    protected void addForms(Model model, UserDetailsImpl userDetails){
        if (model.getAttribute("profileEditForm")==null){
            model.addAttribute("profileEditForm", new ProfileEditForm(userDetails.getUser()));
        }
        if (model.getAttribute("passwordEditForm")==null){
            model.addAttribute("passwordEditForm", new PasswordEditForm());
        }
        if (model.getAttribute("accountDeleteForm")==null){
            model.addAttribute("accountDeleteForm", new AccountDeleteForm());
        }
    }
}
