
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zarpy
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "personList", cascade = CascadeType.PERSIST)
    private ArrayList<Phone> phoneNumbers;
    @ManyToMany (mappedBy = "personList", cascade = CascadeType.PERSIST)
    private ArrayList hobbyList;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Address address;

    public Person(String email, String firstName, String lastName, ArrayList<Phone> phoneNumbers, ArrayList hobbyList, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.hobbyList = hobbyList;
        this.address = address;
    }
    
    

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(ArrayList hobbyList) {
        this.hobbyList = hobbyList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
