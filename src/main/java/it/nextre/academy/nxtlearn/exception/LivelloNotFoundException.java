package it.nextre.academy.nxtlearn.exception;

public class LivelloNotFoundException extends NotFoundException {
    public LivelloNotFoundException() {
        this("LIVELLO NOT FOUND");
    }

    public LivelloNotFoundException(String msg) {
        super(msg);
    }
}//end class