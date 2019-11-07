package it.nextre.academy.nxtlearn.controller;

import it.nextre.academy.nxtlearn.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// https://www.baeldung.com/exception-handling-for-rest-with-spring
// https://en.wikipedia.org/wiki/List_of_HTTP_status_codes

@ControllerAdvice("it.nextre.academy.nxtlearn.controller")
public class ExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound2(NotFoundException ex){
        logger.debug("ControllerAdvice NotFoundException");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/404.html");
        mv.setStatus(HttpStatus.NOT_FOUND);
        mv.addObject("mex", ex.getMessage());
        return mv;
    }
}//end class

/*
//NON SERVE, X NOI SERVE SOLO NELLE REST
class MyErrorPage{
    private HttpStatus statusCode;
    private Object body;
    MyErrorPage(HttpStatus statusCode, Object body){
        this.statusCode=statusCode;
        this.body=body;
    }
    public ResponseEntity getPage(){
        return new ResponseEntity(body,statusCode);
    };
}
*/
