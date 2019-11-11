package it.nextre.academy.nxtlearn.exception;

public class GuidaNotFoundException extends NotFoundException {

    public GuidaNotFoundException() {
        this("GUIDA NOT FOUND");
    }

    public GuidaNotFoundException(String msg) {
        super(msg);
    }
}//end class
