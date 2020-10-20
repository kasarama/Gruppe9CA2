package facades;

import dto.PersonDTO;
import dto.HobbyDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import entities.Address;
import java.util.ArrayList;




public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO);

    public PersonDTO editPerson(PersonDTO p); 

    public PersonListDTO getPersonListByHobby(String hobbyName); 
    
    public PersonDTO getPersonByNumber(int number);

}
