package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.NotFoundException;
import it.nextre.academy.nxtlearn.exception.PersonaAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.*;

// https://www.baeldung.com/exception-handling-for-rest-with-spring
// https://en.wikipedia.org/wiki/List_of_HTTP_status_codes


@RestControllerAdvice("it.nextre.academy.nxtlearn.api")
public class ExceptionHandlerRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException ex){
        logger.debug("RestControllerAdvice NotFoundException");
        return new MyError(HttpStatus.NOT_FOUND, ex.getMessage())
                //.addHeader("header-1","text-1")
                //.addData("body-example-1",123456789)
                .getPage();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequest(BadRequestException ex){
        logger.debug("RestControllerAdvice BadRequestException");
        return new MyError(HttpStatus.BAD_REQUEST, ex.getMessage())
                .getPage();
    }

    @ExceptionHandler(PersonaAlreadyExistsException.class)
    public ResponseEntity handleBadRequest(PersonaAlreadyExistsException ex){
        logger.debug("RestControllerAdvice PersonaAlreadyExistsException");
        return new MyError(HttpStatus.BAD_REQUEST, ex.getMessage())
                .getPage();
    }


}//end class

class MyError{
    private HttpStatus statusCode;
    private Map<String,Object> data = new HashMap<>();
    private MultiValueMap<String, String> customHeaders = new HttpHeaders();
    MyError(HttpStatus statusCode, Object body){
        this.statusCode=statusCode;
        this.data.put("data",body);
        this.data.put("status",statusCode.value());
        this.data.put("error",statusCode.toString().substring(4).trim());
        this.data.put("timestamp", Instant.now().toEpochMilli());
    }
    public ResponseEntity getPage(){
        return new ResponseEntity(data,statusCode);
    };

    public MyError addData(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public MyError addHeader(String key, String value){
        this.customHeaders.put(key, Arrays.asList(value));
        return this;
    }
    public MyError addHeaders(String key, List<String> value){
        this.customHeaders.put(key, value);
        return this;
    }
}