
package dto;

/**
 *
 * @author magda og søren
 */
public class PhoneDTO {
    private Integer number;
    private String description;

    public PhoneDTO(Integer number, String description) {
        this.number = number;
        this.description = description;
    }

    public Integer getNumber() {
        return number;
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
