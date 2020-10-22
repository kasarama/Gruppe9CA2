/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author magda
 */
public class PersonListDTO {

    private ArrayList<PersonDTO> personList = new ArrayList();

    public PersonListDTO() {
    }

    public PersonListDTO(Set<Person> personList) {
       
        for (Person person : personList) {
            PersonDTO personDTO = new PersonDTO(person);
            this.personList.add(personDTO);

        }
    }

    public ArrayList<PersonDTO> getList() {
        return this.personList;
    }

}
