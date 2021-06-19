package ru.itis.javalab.server.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.javalab.server.annotations.LogTransient;
import ru.itis.javalab.server.annotations.Log;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ExceptionHandler(GithubAuthorizationException.class)
    public String handleDataExceptions(GithubAuthorizationException exception, RedirectAttributes attributes){
        if (exception instanceof GithubConnectionException){
            attributes.addFlashAttribute("error", "errors.github.connection");
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        }
        else if (exception instanceof UnsuccessfulGithubAuthorizationException){
            attributes.addFlashAttribute("error", "errors.github.failed_login");
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        }
        else if (exception instanceof GithubDuplicateLoginException){
            attributes.addFlashAttribute("error", "errors.github.duplicate_login");
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        }
        else {
            attributes.addFlashAttribute("error", "errors.github.unknown_exception");
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    @LogTransient
    public String accessDeniedExceptionHandler(AccessDeniedException accessDeniedException,
                                               RedirectAttributes attributes){
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken){
            attributes.addFlashAttribute("message", "errors.not_authorized_user");
//            return "redirect:/sign-in";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#getSignInPage").build();
        } else {
            attributes.addFlashAttribute("message", "errors.authorized_user");
//            return "redirect:/profile";
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfilePage").build();
        }
    }

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Exception exception,  RedirectAttributes attributes){
        System.out.println(exception.getClass().getName());
        attributes.addFlashAttribute("/exception", true);
        attributes.addFlashAttribute("message", "HEEEEEEH?!?!? \n " +
                "(There is no reason for this exception, so I'm real'y teapot)");
//        return "redirect:/exception";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#handleException").build();
    }

}
