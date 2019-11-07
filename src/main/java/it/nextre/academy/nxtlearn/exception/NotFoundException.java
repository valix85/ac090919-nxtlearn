package it.nextre.academy.nxtlearn.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        this("NOT FOUND");
    }
    public NotFoundException(String mex){
        super(mex);
    }
}//end class
