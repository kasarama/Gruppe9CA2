/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.CityInfo;
import java.util.List;

/**
 *
 * @author Selina A.S.
 */
public class AddressDTO {
    private String street;
    private String additionalInfo;
    private List<PersonDTO> persons;
    private CityInfo cityInfo;
    
    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = address.getCityInfo();
    }
    
    public AddressDTO(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
    }
    
    //Not sure, if there should be any empty constructor.
    public AddressDTO() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTO> persons) {
        this.persons = persons;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

}
    

