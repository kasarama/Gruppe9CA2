package facades;

import dto.PersonDTO;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;


public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO) throws MissingInputException;

    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException; 
    
    public PersonDTO getPersonByNumber(int number);

}
