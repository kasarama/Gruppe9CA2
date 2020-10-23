
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    @OneToMany(mappedBy="owner", cascade = CascadeType.PERSIST)
    private List<Phone> phoneNumbers = new ArrayList();
    
    @ManyToMany (mappedBy = "personList", cascade = CascadeType.PERSIST)
    private Set<Hobby> hobbyList = new HashSet<>();
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Address address;

    public Person() {
    }

    
    
    public Person(String email, String firstName, String lastName,  Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = new ArrayList<>();
        this.hobbyList = new HashSet<>();
        this.address = address;
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
        public void addAddress(Address address) {
        this.address = address;
        if (address != null) {
            this.address = address;
            address.addPerson(this);
        } else {
            this.address = null;   
        }
    }
    public void addPhone(Phone phone) {
        this.phoneNumbers.add(phone);
        if (phone.getOwner() != this) {
            phone.setOwner(this);
        }
    }
    public void addHobby(Hobby hobby){
        this.hobbyList.add(hobby);
        if (!hobby.getPersonList().contains(this)){
        hobby.addPerson(this);
        }
    }
    
    public void removePhone(Phone phone){
        this.phoneNumbers.remove(phone);
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

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(HashSet hobbyList) {
        this.hobbyList = hobbyList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
