/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonListDTO;
import entities.Hobby;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    
}
