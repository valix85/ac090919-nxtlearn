package it.nextre.academy.nxtlearn.exception;

public class PersonaNotFoundException extends NotFoundException {
    public PersonaNotFoundException(){
        this("PERSONA NOT FOUND");
    }
    public PersonaNotFoundException(String mex){
        super(mex);
    }
}//end class
