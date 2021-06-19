package ru.itis.javalab.data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.javalab.data.annotations.Log;

@ControllerAdvice
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataException.class)
    public ResponseEntity<?> handleDataExceptions(DataException exception){
        if (exception instanceof EntityNotFoundException){
            return ResponseEntity.notFound().build();
        }
        else if (exception instanceof EntityNotExistsException){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @ExceptionHandler(AuthorizeException.class)
    public ResponseEntity<?> handleAuthorizeExceptions(AuthorizeException exception){
        if (exception instanceof IncorrectJwtException){
            return new ResponseEntity<>("Incorrect token.", HttpStatus.FORBIDDEN);
        }
        else if (exception instanceof IncorrectUserDataException){
            return new ResponseEntity<>("Incorrect user data.", HttpStatus.FORBIDDEN);
        }
        else if (exception instanceof ExpiredJwtException){
            return new ResponseEntity<>("The token is expired. Try to get new", HttpStatus.FORBIDDEN);
        }
        else if (exception instanceof BannedUserException){
            return new ResponseEntity<>("You got banned, nerd!", HttpStatus.FORBIDDEN);
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedExceptionHandler(){
        return new ResponseEntity<>("You have no access to this service.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultExceptionHandler(Exception exception){
        return new ResponseEntity<>("HEEEEEEH?!?!? \n " +
                "(There is no reason for this exception, so I'm real'y teapot)", HttpStatus.I_AM_A_TEAPOT);
    }

}
