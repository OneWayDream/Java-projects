package ru.itis.javalab.server.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.javalab.server.annotations.Log;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.security.details.UserDetailsImpl;
import javax.annotation.security.PermitAll;

@Controller
@Log
public class UtilsController {

    @PermitAll
    @GetMapping
    public String getRoot(Authentication authentication) {
        if (authentication != null) {
//            return "redirect:/profile";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfilePage").build();
        } else {
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        }
    }

    @PermitAll
    @RequestMapping(value = "/exception",
            method = RequestMethod.GET)
    public String handleException(Model model){
        if(model.getAttribute("/exception")!=null){
            try{
                UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (userDetails != null){
                    model.addAttribute("user", userDetails.getUser());
                }
            } catch (ClassCastException ex){
                //ignore, its normal for AnonymousAuthentication
            }
            return "exception";
        }
//        return "redirect:/main";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#getMainPage").build();
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/success",
            method = RequestMethod.GET)
    public String handleSuccessAction(Model model){
        if(model.getAttribute("/success")!=null){
            return "success";
        }
//        return "redirect:/main";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#getMainPage").build();
    }

}
