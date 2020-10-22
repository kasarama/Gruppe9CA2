
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
    private String wikiLink;
    private ArrayList<PersonDTO> personList;
    private String category;
    private String type;

    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.wikiLink = hobby.getWikiLink();
        this.personList = new ArrayList();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
        
    }

    public HobbyDTO(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
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



    public String getWikiLink() {
        return wikiLink;
    }


    public ArrayList<PersonDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<PersonDTO> personList) {
        this.personList = personList;
    }

    public String getCategory() {
        return category;
    }


    public String getType() {
        return type;
    }
    
}
