/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class PersonListDTO {
    private ArrayList<PersonDTO> personList;

    public PersonListDTO(ArrayList<Person> personList) {
        this.personList = new ArrayList();
        for (Person person : personList) {
            PersonDTO personDTO = new PersonDTO(person);
            
        }
    }
    
    
}
