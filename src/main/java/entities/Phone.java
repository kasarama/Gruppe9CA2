/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author zarpy
 */
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Person owner;
    

    public Phone() {
    }

    public Phone(Integer number, String description) {
        this.number = number;
        this.description = description;
        
    }
    
    

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getOwner() {
        return owner;
    }
    
    public void setOwner(Person person) {
        if(this.owner != null)
        this.owner.removePhone(this);
        this.owner = person;        
      
        if (!person.getPhoneNumbers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            person.getPhoneNumbers().add(this);
        }
    }
    

    
}
