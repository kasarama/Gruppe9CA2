
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

    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.personList = new ArrayList();
        
    }

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
    
}
