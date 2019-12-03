package it.nextre.academy.nxtlearn.exception;

public class NotAllowedException extends RuntimeException{
    public NotAllowedException(){
        this("NOT ALLOWED");
    }
    public NotAllowedException(String mex){
        super(mex);
    }
}//end class
