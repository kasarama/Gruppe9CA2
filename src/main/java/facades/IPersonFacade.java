package facades;

import dto.PersonDTO;


public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO);

    public PersonDTO editPerson(PersonDTO p); 
    
    public PersonDTO getPersonByNumber(int number);

}
