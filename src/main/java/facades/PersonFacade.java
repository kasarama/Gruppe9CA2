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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        try {
            em.getTransaction().begin();

            addressEntity.setCityInfo(em.find(CityInfo.class, personDTO.getAddress().getZip()));
            System.out.println("addressEntity info: " + addressEntity.getCityInfo().getCity());
            Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName(), addressEntity);

            for (PhoneDTO phoneDTO : personDTO.getPhoneList()) {
                Phone phoneEntity = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
                person.addPhone(phoneEntity);
            }
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
        EntityManager em = emf.createEntityManager();
        PersonDTO editedPersonDTO;
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, p.getId());

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

    public PersonDTO deletePerson(int id) {

        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);

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

    //todo implement endpoints for the following methods
    public PhoneListDTO deletePhone(int id, int number) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Given phone: "+number+"\ngiven id: "+id);
        try {
            System.out.println("trying hard");
            em.getTransaction().begin();
            System.out.println("Transaktion started");
            
            Person person = em.find(Person.class, id);
            System.out.println("...---...");
            System.out.println("prson found: "+person.getFirstName());
            System.out.println("Found person has somenumbers?: "+person.getPhoneNumbers().size());
            Phone phone = new Phone();
            for (Phone existing : person.getPhoneNumbers()) {
                if (existing.getNumber() == number) {
                    phone = existing;
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
