
package dto;

import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magda og søren
 */
public class PhoneListDTO {
    
    private ArrayList<PhoneDTO> phoneList=new ArrayList();

    public PhoneListDTO(List<Phone> phoneList) {
        for (Phone phone : phoneList) {
            PhoneDTO phoneDTO = new PhoneDTO(phone);
            this.phoneList.add(phoneDTO);            
        }                
    }

    public ArrayList<PhoneDTO> getPhoneList() {
        return phoneList;
    }
    
    
    
    
}
