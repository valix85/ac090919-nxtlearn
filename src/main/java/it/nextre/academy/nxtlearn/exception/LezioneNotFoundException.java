package it.nextre.academy.nxtlearn.exception;

public class LezioneNotFoundException extends NotFoundException {
    public LezioneNotFoundException() {
        this("LEZIONE NOT FOUND");
    }

    public LezioneNotFoundException(String msg) {
        super(msg);
    }
}//end class