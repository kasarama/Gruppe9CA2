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
public class PersonFacade implements IPersonFacade{
    
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }


    @Override
   // public PersonDTO addPerson(String email, String firstName, String lastName,  ArrayList<PhoneDTO> phoneNumberList,  ArrayList<HobbyDTO> hobbyList, Address address) {
       public PersonDTO addPerson(PersonDTO personDTO){ 
       EntityManager em = emf.createEntityManager();
      
       ArrayList<Phone> phoneListEntity =new ArrayList();
        for (PhoneDTO phoneDTO : personDTO.getPhoneList()) {
            Phone phoneEntity = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
            phoneListEntity.add(phoneEntity);            
        }
        
        HashSet<Hobby> hobbyListEntity = new HashSet();
        for (HobbyDTO hobbyDTO : personDTO.getHobbyList()) {
             Hobby hobby = new Hobby(hobbyDTO.getName(),hobbyDTO.getDescription());
             hobbyListEntity.add(hobby);    
             
        }
        //todo implement addressEntity so it is dat given with addressDTO insted of new Address()
        Address addressEntity = new Address();
               
        
        Person person = new Person(personDTO.getEmail(),personDTO.getFirstName(), personDTO.getLastName(),phoneListEntity,hobbyListEntity, addressEntity );
        
        try {
          em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonListDTO getPersonListByHobby(String hobbyName) {
       
       /*
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> query =
                    em.createQuery("SELECT p FROM Person p LEFT JOIN p.hobbyList h where h.name=? ", Person.class);
            
        return query.getResultList();
        } finally {
            em.close();
        
        }
*/throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO getPersonByNumber(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
