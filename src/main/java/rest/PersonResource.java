/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonListDTO;
import dto.PhoneDTO;
import dto.PhoneListDTO;
import facades.AddressFacade;
import facades.HobbyFacade;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Søren and Magda
 */
@Path("person")
public class PersonResource {

     @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final HobbyFacade FACADEHOBBY = HobbyFacade.getHobbyFacade(EMF);
    private static final AddressFacade FACADEADDRESS = AddressFacade.getAddressFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNewPerson(String PersonJson){
        PersonDTO newPerson = GSON.fromJson(PersonJson, PersonDTO.class);
        
        PersonDTO addedPerson = FACADE.addPerson(newPerson);
        
        
        return GSON.toJson(addedPerson);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editPerson(String personJSON, @PathParam("id") int id){
        PersonDTO dto = GSON.fromJson(personJSON, PersonDTO.class);
        dto.setId(id);
        PersonDTO edited = FACADE.editPerson(dto);
        
        return GSON.toJson(edited);
    }
    
    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") int id){
        PersonDTO deleted = FACADE.deletePerson(id);
        return GSON.toJson(deleted);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("allpeople")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPeople() {
        
        return GSON.toJson(FACADE.getAllPersons().getList());
    }
    
    @Path("livingin/{city}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPeopleInCity(@PathParam("city") String city){
        return GSON.toJson(FACADE.getallFromCity(city).getList());
    }
    
    
    @Path("quantityofliking/{hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPeopleLiking(@PathParam("hobby") String hobbyName){
        return GSON.toJson(FACADE.countPeopleWithHobby(hobbyName));
    }
    
    
    @Path("hobbylist/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String hobbyListOfPerson(@PathParam("id") int id){
        return GSON.toJson(FACADE.personsHobbyList(id).getList());
    }
    
    
    @Path("phonelist/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String phoneListOfPerson(@PathParam("id") int id){
        return GSON.toJson(FACADE.personsPhoneList(id).getPhoneList());
    }
    
    //checked
    @Path("deletephone/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deletePhone(@PathParam("id") int id, String phoneJson){        
        PhoneDTO phone =GSON.fromJson(phoneJson, PhoneDTO.class);       
        return GSON.toJson(FACADE.deletePhone(id, phone.getNumber()).getPhoneList());
    }
    
    //checked
    @Path("deletehobby/{name}&{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteHobby(@PathParam("id") int id, @PathParam("name") String name){
        return GSON.toJson(FACADE.deleteHobby(id, name).getList());
    }
    
    //checked
    @Path("addphone/{id}")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPhone(String phoneJson, @PathParam("id")int id){
        
        PhoneDTO newPhone = GSON.fromJson(phoneJson, PhoneDTO.class);
        
       PhoneListDTO phoneList = FACADE.addPhone(id,newPhone);
        
        
        return GSON.toJson(phoneList.getPhoneList());
    }
    //checked
    @Path("addhobby/{id}")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addHobby(String hobbyJson, @PathParam("id")int id){
      HobbyDTO hobby = GSON.fromJson(hobbyJson, HobbyDTO.class);
        
        return GSON.toJson(FACADE.addHobby(id, hobby.getName()).getList());
    }
    
    @Path("personlist/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsByHobby(@PathParam("name") String name) {
        PersonListDTO list = FACADEHOBBY.getPersonList(name);
        return GSON.toJson(list.getList());
    }
    
    @Path("peoplebyzip/{zip}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPeopleByZip(@PathParam("zip") String zip){
        return GSON.toJson(FACADEADDRESS.getAllPersonsByZip(zip));
    }
    
    @Path("allzipcodes/")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllZipCodes(){
        return GSON.toJson(FACADEADDRESS.getAllZipCodes());
    }
}
