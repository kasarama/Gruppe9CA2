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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    @Column(name = "Street", length = 30, nullable = false)
    private String street;
    @Column(name = "AdditionalInfo", length = 30, nullable = false)
    private String additionalInfo;

    //Fixed, added cascade and then the mappedby worked.
    @OneToMany(mappedBy = "address",
            cascade = CascadeType.PERSIST)
    private List<Person> personList;

    //Fixed, changed mappedBy to cascade
    @JoinColumn(name = "ZipCode")
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
            person.setAddress(this);
        }
    }

   
}
