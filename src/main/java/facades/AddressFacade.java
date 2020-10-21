package facades;

import dto.AddressDTO;
//import dto.CityInfoDTO;
import dto.PersonDTO;
import entities.Address;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Selina A.S.
 */
public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    private AddressFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class
     */
    public static AddressFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AddressDTO addAddress(AddressDTO address) throws Exception {
        EntityManager em = emf.createEntityManager();
        Address addAddress = new Address();
        addAddress.setStreet(address.getStreet());
        addAddress.setAdditionalInfo(address.getAdditionalInfo());
        try {
            em.getTransaction().begin();
            em.persist(addAddress);
            em.getTransaction().commit();
            return address;
        } catch (Exception ex) {
            throw new Exception("The address already exists");
        } finally {
            em.close();
        }

    }

    public AddressDTO findAddress(String street) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Address addAddress = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE"
                    + "a.street = :address", Address.class)
                    .setParameter("Address", street);
            em.getTransaction().commit();
            addAddress = query.getSingleResult();
            return new AddressDTO(addAddress);
        } catch (Exception e) {
            throw new NotFoundException("The address is not found");
        } finally {
            em.close();
        }
    }
//public List<CityInfoDTO> getAllZipCodes(){
//    EntityManager em = emf.createEntityManager();
//    try {
//        List<CityInfoDTO> list = em.createQuery("SELECT c FROM CityInfo c").getResultList();
//        return list;
//    } finally {
//        em.close();
//    }
//}

    public List<PersonDTO> getAllPersonsByZip(String zipCode) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Person> persons = em.createQuery("SELECT p FROM Person p "
                    + "JOIN p.address pa JOIN pa.cityInfo pac WHERE pac.zipCode = :zipCode", Person.class)
                    .setParameter("zipCode", zipCode)
                    .getResultList();
            List<PersonDTO> personDTOS = new ArrayList();
            for (Person person : persons) {
                personDTOS.add(new PersonDTO(person));
            }
            return personDTOS;
        } catch (NoResultException ex) {
            throw new NotFoundException("The person you're looking for cannot be found in given city");
        } finally {
            em.close();
        }
    }
}
