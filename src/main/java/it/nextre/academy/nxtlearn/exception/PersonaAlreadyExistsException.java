package it.nextre.academy.nxtlearn.exception;

public class PersonaAlreadyExistsException extends NotFoundException {
    public PersonaAlreadyExistsException(){
        this("PERSONA ALREADY EXISTS");
    }
    public PersonaAlreadyExistsException(String mex){
        super(mex);
    }
}//end class
