package facades;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import entities.RenameMe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;


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
    private Person person1;
    private Person person2;
    
    
    
    
    
    
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
        HashSet<Hobby> hobbyL1= new HashSet();
        hobbyL1.add(hobby1);
        hobbyL1.add(hobby2);
        HashSet<Hobby> hobbyL2= new HashSet();
        hobbyL2.add(hobby1);
       person1=new Person("email@email", "Magda", "Wawrzak",address1);
       person1.addHobby(hobby1);
       person1.addPhone(phone1);
       person1.addPhone(phone2);
              
       
       person2=new Person("email@com", "Bob", "Sponge",  address2);
       person2.addHobby(hobby1);
       person2.addHobby(hobby2);
       person2.addPhone(phone3);
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.persist(person1);
            em.persist(person2);

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
        hobbyList.add(new HobbyDTO("Walking","slowwwwely"));
        hobbyList.add(new HobbyDTO("Eating","Sweets"));
        ArrayList<PhoneDTO> phoneList = new ArrayList();
        phoneList.add(new PhoneDTO(123456, "private"));
        phoneList.add(new PhoneDTO(787897897, "work"));
        personDTO.setAddress(addressDTO);
        personDTO.setEmail("gmail");
        personDTO.setFirstName("Elmo");
        personDTO.setLastName("Blue");
        personDTO.setHobbyList(hobbyList);
        personDTO.setPhoneList(phoneList);
        assertTrue("Elmo".equals(facade.addPerson(personDTO).getFirstName()), "Expects two rows in the database");
    }
    
    @Disabled
    @Test
    public void testGetPersonListByHobby() {
        String hobbyName = hobby1.getName();
        PersonListDTO result = facade.getPersonListByHobby(hobbyName);
        assertTrue(result.getList().contains(person1));
        assertTrue(result.getList().contains(person2));
    }
}


