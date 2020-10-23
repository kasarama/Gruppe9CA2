package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Selina A.S.
 */

@Entity
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 4)
    private String zipCode;
    @Column(length=35)
    private String city;

//    
//    @OneToMany(mappedBy = "cityInfo")
//    List<Address> addressList;
//    private Address address; 

    public CityInfo() {
    }

    public CityInfo(String zipCode) {
        this.zipCode = zipCode;
        this.city = "Default"; 
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }
    
}

