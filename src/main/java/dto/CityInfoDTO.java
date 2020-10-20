/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.CityInfo;

/**
 *
 * @author Selina A.S.
 */
public class CityInfoDTO {
    private String city;
    private String zipcode;

    public CityInfoDTO(String city, String zipcode) {
        this.city = city;
        this.zipcode = zipcode;
    }
    
    public CityInfoDTO(CityInfo cityinfo) {
        this.city = cityinfo.getCity();
        this.zipcode = cityinfo.getZipcode();
    }
    
    //Not sure, if there should be any empty constructor.
    public CityInfoDTO () {
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
    
    
}
