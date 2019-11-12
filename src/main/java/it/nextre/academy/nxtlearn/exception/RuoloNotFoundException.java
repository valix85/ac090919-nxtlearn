package it.nextre.academy.nxtlearn.exception;

public class RuoloNotFoundException extends NotFoundException {
    public RuoloNotFoundException() {
        this("RUOLO NOT FOUND");
    }

    public RuoloNotFoundException(String msg) {
        super(msg);
    }
}//end class