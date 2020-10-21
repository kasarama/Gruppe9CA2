
package dto;

import entities.Hobby;
import entities.Person;
import java.util.ArrayList;

/**
 *
 * @author magda og søren
 */
public class HobbyDTO {
    private String name;
    private String description;
    private ArrayList<PersonDTO> personList;
    private String type;
    private String location;

    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.personList = new ArrayList();
        this.type = hobby.getType();
        this.location = hobby.getLocation();
        
    }

    public HobbyDTO(String name, String description, String type, String location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }
    
    
    /*
    public ArrayList<PersonDTO> convertAllPerson(ArrayList<Person> persons){
        ArrayList personsDTO = new ArrayList<PersonDTO>();
        for (Person person : persons) {
            
            
            
        }
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PersonDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<PersonDTO> personList) {
        this.personList = personList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
