package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author magda og søren
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {

        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        EntityManager em = emf.createEntityManager();

        Address addressEntity = new Address();
        addressEntity.setAdditionalInfo(personDTO.getAddress().getAdditionalInfo());
        addressEntity.setStreet(personDTO.getAddress().getStreet());
//        addressEntity.setCity(personDTO.getAddress().getCity());

        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName(), addressEntity);

        for (PhoneDTO phoneDTO : personDTO.getPhoneList()) {
            Phone phoneEntity = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
            person.addPhone(phoneEntity);
        }

        try {
            em.getTransaction().begin();
            for (HobbyDTO hDTO : personDTO.getHobbyList()) {
                Hobby h = em.find(Hobby.class, hDTO.getName());
                person.addHobby(h);
            }

            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }

    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   

    @Override
    public PersonDTO getPersonByNumber(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
