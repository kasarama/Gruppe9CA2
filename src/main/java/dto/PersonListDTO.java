/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magda
 */
public class PersonListDTO {

    private ArrayList<PersonDTO> personList = new ArrayList();

    public PersonListDTO() {
    }

    public PersonListDTO(List<Person> personList) {
        this.personList = new ArrayList();
        for (Person person : personList) {
            PersonDTO personDTO = new PersonDTO(person);

        }
    }

    public ArrayList<PersonDTO> getList() {
        return this.personList;
    }

}
