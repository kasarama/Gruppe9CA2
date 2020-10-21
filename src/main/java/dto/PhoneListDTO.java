
package dto;

import entities.Phone;
import java.util.ArrayList;

/**
 *
 * @author magda og søren
 */
public class PhoneListDTO {
    
    private ArrayList<PhoneDTO> phoneList=new ArrayList();

    public PhoneListDTO(ArrayList<Phone> phoneList) {
        for (Phone phone : phoneList) {
            PhoneDTO phoneDTO = new PhoneDTO(phone);
            this.phoneList.add(phoneDTO);            
        }
                
    }
    
    
    
}
