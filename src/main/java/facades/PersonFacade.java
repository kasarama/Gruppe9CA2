package facades;

import dto.HobbyDTO;
import dto.HobbyListDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import dto.PhoneListDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import static org.eclipse.persistence.sessions.remote.corba.sun.TransporterHelper.id;

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
    public PersonDTO addPerson(PersonDTO personDTO) throws MissingInputException {
        if (personDTO.getFirstName() == null || personDTO.getLastName() == null) {
            throw new MissingInputException("First Name and/or Last Name is missing");
        } else
        if (personDTO.getAddress() ==  null) {
            throw new MissingInputException("Address is missing");
        } else {
        
        EntityManager em = emf.createEntityManager();

        Address addressEntity = new Address();
        addressEntity.setAdditionalInfo(personDTO.getAddress().getAdditionalInfo());
        addressEntity.setStreet(personDTO.getAddress().getStreet());

        try {
            em.getTransaction().begin();

            addressEntity.setCityInfo(em.find(CityInfo.class, personDTO.getAddress().getZip()));

            Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName(), addressEntity);

            if (personDTO.getPhoneList() != null) {
                for (PhoneDTO phoneDTO : personDTO.getPhoneList()) {
                    Phone phoneEntity = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
                    person.addPhone(phoneEntity);
                }
            }

            if (personDTO.getHobbyList() != null) {
                for (HobbyDTO hDTO : personDTO.getHobbyList()) {
                    Hobby h = em.find(Hobby.class, hDTO.getName());
                    person.addHobby(h);
                }
            }
            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
        }
    }

    
    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException {
        if (p.getFirstName() == null || p.getLastName() == null) {
            throw new MissingInputException("First Name and/or Last Name is missing");
        }
        EntityManager em = emf.createEntityManager();
        PersonDTO editedPersonDTO;
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, p.getId());
            if (p == null) {
            throw new PersonNotFoundException("Could not edit, provided id: " + p.getId() + " does not exist");
        }

            person.setEmail(p.getEmail());
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            em.getTransaction().commit();
            editedPersonDTO = new PersonDTO(person);

            return editedPersonDTO;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByNumber(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PersonDTO deletePerson(int id) throws PersonNotFoundException{

        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
            if (p == null) {
            throw new PersonNotFoundException("Could not delete, provided id: " + id + " does not exist");
        }
        
        
        PersonDTO dto = new PersonDTO(p);

        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();

            return dto;

        } finally {
            em.close();
        }
    }

    public PersonListDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            Set<Person> allPersons = new HashSet<>();
            for (Person person : query.getResultList()) {
                allPersons.add(person);
            }

            return new PersonListDTO(allPersons);

        } finally {
            em.close();
        }
    }

    public int countPeopleWithHobby(String hobbyName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            TypedQuery query = em.createQuery(
                    "SELECT h FROM Hobby h WHERE h.name=:name", Hobby.class)
                    .setParameter("name", hobbyName);
            Hobby result = (Hobby) query.getSingleResult();
            return result.getPersonList().size();

        } finally {
            em.close();
        }
    }

    public HobbyListDTO personsHobbyList(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            ArrayList<Hobby> hobbies = new ArrayList();
            person.getHobbyList().forEach(hobby
                    -> hobbies.add(hobby));
            return new HobbyListDTO(hobbies);
        } finally {
            em.close();
        }

    }

    public PhoneListDTO personsPhoneList(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);

            return new PhoneListDTO(person.getPhoneNumbers());
        } finally {
            em.close();
        }

    }

    public PersonListDTO getallFromCity(String city) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a "
                + "JOIN a.cityInfo c WHERE c.city=:city",
                Person.class).setParameter("city", city);
        Set<Person> result = new HashSet();
        for (Person person : query.getResultList()) {
            result.add(person);
        }
        return new PersonListDTO(result);

    }

    //todo implement endpoints for the following methods
    public PhoneListDTO deletePhone(int id, int number) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);

            Phone phone = new Phone();
            for (Phone existing : person.getPhoneNumbers()) {
                if (existing.getNumber() == number) {
                    phone = existing;
                    em.remove(existing);
                }
            }
            person.getPhoneNumbers().remove(phone);
            em.getTransaction().commit();

            return new PhoneListDTO(person.getPhoneNumbers());
        } finally {
            em.close();
        }
    }

    public PhoneListDTO addPhone(int id, PhoneDTO newPhone) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            person.addPhone(new Phone(newPhone));
            em.getTransaction().commit();

            return new PhoneListDTO(person.getPhoneNumbers());
        } finally {
            em.close();
        }

    }

    public HobbyListDTO deleteHobby(int id, String hobbyName) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);

            for (Hobby hobby : person.getHobbyList()) {
                if (hobby.getName().equals(hobbyName)) {
                    hobby.getPersonList().remove(person);
                    person.getHobbyList().remove(hobby);
                }
            }

            em.getTransaction().commit();
            List<Hobby> updated = new ArrayList();
            for (Hobby hobby : person.getHobbyList()) {
                updated.add(hobby);
            }
            return new HobbyListDTO(updated);
        } finally {
            em.close();
        }
    }

    public HobbyListDTO addHobby(int id, String hobbyName) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);

            Hobby hobby = em.find(Hobby.class, hobbyName);

            person.addHobby(hobby);

            em.getTransaction().commit();
            List<Hobby> updated = new ArrayList();
            for (Hobby h : person.getHobbyList()) {
                updated.add(h);
            }
            return new HobbyListDTO(updated);
        } finally {
            em.close();
        }
    }

}
