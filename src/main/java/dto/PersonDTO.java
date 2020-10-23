package dto;

import entities.Person;
import java.util.ArrayList;

/**
 *
 * @author magda og søren
 */
public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<PhoneDTO> phoneList;
    private ArrayList<HobbyDTO> hobbyList;
    private AddressDTO address;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.phoneList=new ArrayList<>();
        person.getPhoneNumbers().forEach(phone -> {
            this.phoneList.add(new PhoneDTO(phone));
        });
        this.hobbyList= new ArrayList<>();
        person.getHobbyList().forEach(hobby -> {
            this.hobbyList.add(new HobbyDTO(hobby));
        });
        this.address = new AddressDTO(person.getAddress());
    }

   

    public PersonDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<PhoneDTO> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<PhoneDTO> phoneList) {
        this.phoneList = phoneList;
    }

    public ArrayList<HobbyDTO> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(ArrayList<HobbyDTO> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
