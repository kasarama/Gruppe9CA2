/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author søren og magda
 */
@Entity
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 50)
    private String name;
    private String wikiLink;
    private String category;
    private String type;   
    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(name="PERSON_HOBBY") // Owning side
    private Set<Person> personList = new HashSet();

        
    public Hobby() {
    }
   
    
    public void addPerson(Person person){
        this.personList.add(person);
        if(!person.getHobbyList().contains(this)){
        person.addHobby(this);
        }
    }

    public Hobby(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }
     
   

    public String getName() {
        return name;
    }   

    public String getWikiLink() {
        return wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }  

    public Set<Person> getPersonList() {
        return personList;
    }

   
    
    
}
