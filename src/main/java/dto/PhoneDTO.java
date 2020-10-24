
package dto;

import entities.Phone;

/**
 *
 * @author magda og søren
 */
public class PhoneDTO {
    private Integer number;
    private String description;

    public PhoneDTO(Phone phone) {
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public PhoneDTO(int number, String description) {
        this.number = number;
        this.description = description;
    }
    
    

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
