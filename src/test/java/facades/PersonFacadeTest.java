package facades;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import entities.Person;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Phone;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

/**
 *
 * @author magda
 */
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade personFacade;
    private static HobbyFacade hobbyFacade;

    private Phone phone1 = new Phone(123123123, "private");
    private Phone phone2 = new Phone(22222222, "home");
    private Phone phone3 = new Phone(333333, "mobil");
    private Person person1;
    private Person person2;
    private String hobbyName1 = "D-udskrivning";
    private String hobbyName2 = "Akrobatik";

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        personFacade = PersonFacade.getPersonFacade(emf);
        hobbyFacade = HobbyFacade.getHobbyFacade(emf);
        EntityManager em = emf.createEntityManager();
        Hobby hobby1 = new Hobby("D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
        Hobby hobby2 = new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
        Hobby hobby3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");

        try {
            em.getTransaction().begin();
            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(new CityInfo("1234"));
            em.persist(new CityInfo("5684"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Address address1 = new Address("Street One", "2.th", em.find(CityInfo.class, "1234"));
            Address address2 = new Address("Street Two", "3.th", em.find(CityInfo.class, "5684"));
            person1 = new Person("email@email", "Magda", "Wawrzak");
            person1.addHobby(em.find(Hobby.class, hobbyName1));
            person1.addPhone(phone1);
            person1.addPhone(phone2);
            person1.addAddress(address1);

            person2 = new Person("email@com", "Bob", "Sponge");
            person2.addHobby(em.find(Hobby.class, hobbyName1));
            person2.addHobby(em.find(Hobby.class, hobbyName2));
            person2.addPhone(phone3);
            person1.addAddress(address2);

            em.persist(person1);
            em.persist(person2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Phone").executeUpdate();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Test
    public void testGetPersonListByHobby() {

        PersonListDTO result = hobbyFacade.getPersonList(hobbyName1);
        assertEquals(result.getList().size(), 2);
    }

    @Test
    public void testAddPerson() {

        PersonDTO personDTO = new PersonDTO();
        AddressDTO addressDTO = new AddressDTO("Sesam Strrt", "666.sd");
        ArrayList<HobbyDTO> hobbyList = new ArrayList();

        hobbyList.add(new HobbyDTO("Akrobatik"));
        hobbyList.add(new HobbyDTO("Skuespil"));

        ArrayList<PhoneDTO> phoneList = new ArrayList();
        phoneList.add(new PhoneDTO(123456, "private"));
        phoneList.add(new PhoneDTO(787897897, "work"));
        personDTO.setAddress(addressDTO);
        personDTO.setEmail("gmail");
        personDTO.setFirstName("Elmo");
        personDTO.setLastName("Blue");
        personDTO.setHobbyList(hobbyList);
        personDTO.setPhoneList(phoneList);

        assertTrue("Elmo".equals(personFacade.addPerson(personDTO).getFirstName()), "Expects two rows in the database");
    }

}
