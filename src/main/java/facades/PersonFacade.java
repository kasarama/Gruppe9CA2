package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
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

//        addressEntity.setCity(personDTO.getAddress().getCity());
        try {
            em.getTransaction().begin();
            
            
            addressEntity.setCityInfo(em.find(CityInfo.class, personDTO.getAddress().getZip()));
            System.out.println("addressEntity info: "+addressEntity.getCityInfo().getCity());
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
            //  
            return dto;

        } finally {
            em.close();
        }
    }
    
    public PersonListDTO getAllPersons(){
         EntityManager em = emf.createEntityManager();
         try {
            em.getTransaction().begin();
            
            
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            Set<Person> allPersons = new HashSet<>();
                   for (Person person :  query.getResultList()){
                 allPersons.add(person);
             }
            
            return new PersonListDTO(allPersons);
            
         }finally {
             em.close();
         }
    }
    
    /*
    Get information about a person (address, hobbies etc) given a phone number
Get all persons living in a given city (i.e. 2800 Lyngby)
Get the number of people with a given hobby
Get a list of all zip codes in Denmark
Create new Persons
Edit Persons

    */
}
