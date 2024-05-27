package br.com.joshua.productchallengeservice.security.exceptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.joshua.productchallengeservice.security.exceptions.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class MainExceptionHandler {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
    	e.printStackTrace();
    	log.info(e.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(500, req.getServletPath(), e.getMessage(), LocalDateTime.now()));
    }
	
}
