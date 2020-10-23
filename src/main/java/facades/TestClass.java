package facades;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author magda
 */
public class TestClass {
    public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);
        PersonDTO personDTO = new PersonDTO();
        AddressDTO addressDTO = new AddressDTO("Sesam Strrt","666.sd","0800");
        ArrayList<HobbyDTO> hobbyList = new ArrayList();
        ArrayList<PhoneDTO> phoneList = new ArrayList();
        personDTO.setAddress(addressDTO);
        personDTO.setEmail("gmail");
        personDTO.setFirstName("Elmo");
        personDTO.setLastName("Blue");
        personDTO.setHobbyList(hobbyList);
        personDTO.setPhoneList(phoneList);
        PersonDTO addedPerson =(PersonDTO) personFacade.addPerson(personDTO);
        System.out.println("New person's id: "+ addedPerson.getId());
    }
    
}
