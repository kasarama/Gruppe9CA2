/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.HobbyDTO;
import dto.PersonListDTO;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author magda
 */
public class HobbyFacade {
    
    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {

        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance; 
    }
    
    
    public PersonListDTO getPersonList(String hobbyName) {
        EntityManager em = emf.createEntityManager();
        try {
            
            Hobby hobby = em.find(Hobby.class, hobbyName);
            return new PersonListDTO(hobby.getPersonList());
        } finally {
            em.close();

        }

    }
    
     public ArrayList<HobbyDTO> getAllHobbies(){
         EntityManager em = emf.createEntityManager();
         try {
            em.getTransaction().begin();
            
            
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
            ArrayList<Hobby> allHobbies = (ArrayList<Hobby>) query.getResultList();
            ArrayList<HobbyDTO> hobbyDTOList = new ArrayList();
             for (Hobby hobby : allHobbies) {
                 hobbyDTOList.add(new HobbyDTO(hobby));
             }
            
            return hobbyDTOList;
            
         }finally {
             em.close();
         }
    }
     
     
    
}
