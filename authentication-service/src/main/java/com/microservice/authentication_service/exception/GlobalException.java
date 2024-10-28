package com.microservice.authentication_service.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.yaml.snakeyaml.constructor.ConstructorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(
            {
                    ConstructorException.class,
                    MissingServletRequestParameterException.class,
                    MethodArgumentNotValidException.class
            }
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "400 Response",
                                    summary = "Handle request data",
                                    value = """
                                        {
                                          "timestamp": "2023-10-19T06:26:34.388+00:00",
                                          "status": 400,
                                          "path": "/accounts/user",
                                          "error": "Invalid request",
                                          "messages": "Email is invalid"
                                        }"""
                            )))
    })

    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid request",
                path,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public Error handleValidationException(Exception e, WebRequest request) {
//        Error error = new Error();
//        error.setTimestamp(new Date());
//        error.setStatus(BAD_REQUEST.value());
//        error.setPath(request.getDescription(false).replace("uri=", ""));
//
//        String message = e.getMessage();
//        if (e instanceof MethodArgumentNotValidException) {
//            int start = message.lastIndexOf("[");
//            int end = message.lastIndexOf("]");
//            message = message.substring(start + 1, end - 1);
//            error.setError("Body is invalid");
//            error.setMessages(message);
//        } else if (e instanceof MissingServletRequestParameterException) {
//            error.setError("Parameter is invalid");
//            error.setMessages(message);
//        } else if (e instanceof ConstraintViolationException) {
//            error.setError("Parameter is invalid");
//            error.setMessages(message.substring(message.indexOf(" ") + 1));
//        } else {
//            error.setError("Data is invalid");
//            error.setMessages(message);
//        }

        return null;
    }
}
