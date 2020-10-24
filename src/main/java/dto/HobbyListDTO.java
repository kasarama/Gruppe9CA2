/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magda
 */
public class HobbyListDTO {
    private ArrayList<HobbyDTO> list = new ArrayList();

    public HobbyListDTO(List<Hobby> hobbies) {
        hobbies.forEach(hobby -> {
            this.list.add(new HobbyDTO(hobby));
        });
    }

    public ArrayList<HobbyDTO> getList() {
        return list;
    }
    
}
