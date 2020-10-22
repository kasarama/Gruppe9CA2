/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;

/**
 *
 * @author Selina A.S.
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String additionalInfo;

    //Fixed, added cascade and then the mappedby worked.
    @OneToMany(mappedBy = "address",
            cascade = CascadeType.PERSIST)
    private List<Person> personList;

    //Fixed, changed mappedBy to cascade
    @ManyToOne()
    private CityInfo cityInfo;
    //private String city;

    public Address() {
    }

    //Mon vi blot skal gøre at personen bliver tilføjet oppe under 
    public Address(String street, String additionalInfo, CityInfo cityInfo) {
        //public Address(String street, String additionalInfo, String city) {

        this.street = street;
        this.additionalInfo = additionalInfo;
        //this.city = city;
        this.personList = new ArrayList();
        this.cityInfo = cityInfo;

    }

    public Address(String street, String additionalInfo, List<Person> personList, CityInfo cityInfo) {
        //public Address(String street, String additionalInfo, List<Person> personList, String city) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.personList = personList;
        //    this.city = city;
        this.cityInfo = cityInfo;
    }
    
    

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//    
    public void addPerson(Person person) {
        this.personList.add(person);
        if (person != null) {
            personList.add(person);
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("email", "Bob", "Belcher");
        CityInfo c1 = new CityInfo("3400");
        Address a1 = new Address("Some street","AdditionalInfo", c1);
           try{
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(p1);
        em.persist(a1);
 
        em.getTransaction().commit();
        
        } finally{
            em.close(); //We close this every request. We keep open the factory
        }
    }   
}
