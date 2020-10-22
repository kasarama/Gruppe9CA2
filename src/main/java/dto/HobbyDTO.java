
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

    public HobbyDTO(String name) {
        this.name = name;

    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public ArrayList<PersonDTO> getPersonList() {
        return personList;
        
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
