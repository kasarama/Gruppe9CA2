package facades;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import entities.RenameMe;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 * @author magda
 */
public class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private Phone phone1= new Phone(123123123,"private");
    private Phone phone2= new Phone(22222222,"home");
    private Phone phone3= new Phone(333333,"mobil");
    private Hobby hobby1 = new Hobby("swiming","Just swimming");
    private Hobby hobby2 = new Hobby("danicng","Just danicng");
    private Address address1= new Address("Street One", "2.th", "Søborg");
    private Address address2= new Address("Street Two", "3.th", "Bagsværd");
    
    
    
    
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        ArrayList<Phone> phoneL1= new ArrayList();
        phoneL1.add(phone1);
        phoneL1.add(phone2);
        ArrayList<Phone> phoneL2= new ArrayList();
        phoneL1.add(phone3);
        ArrayList<Hobby> hobbyL1= new ArrayList();
        hobbyL1.add(hobby1);
        hobbyL1.add(hobby2);
         ArrayList<Hobby> hobbyL2= new ArrayList();
        hobbyL2.add(hobby1);
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.persist(new Person("email@email", "Magda", "Wawrzak", phoneL1, hobbyL1,address1));
            em.persist(new Person("email@com", "Bob", "Sponge", phoneL2, hobbyL2, address2));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddPerson() {
        PersonDTO personDTO = new PersonDTO();
        AddressDTO addressDTO = new AddressDTO("Sesam Strrt","666.sd");
        ArrayList<HobbyDTO> hobbyList = new ArrayList();
        ArrayList<PhoneDTO> phoneList = new ArrayList();
        personDTO.setAddress(addressDTO);
        personDTO.setEmail("gmail");
        personDTO.setFirstName("Elmo");
        personDTO.setLastName("Blue");
        personDTO.setHobbyList(hobbyList);
        personDTO.setPhoneList(phoneList);
        assertTrue("Elmo".equals(facade.addPerson(personDTO).getFirstName()), "Expects two rows in the database");
    }
}


