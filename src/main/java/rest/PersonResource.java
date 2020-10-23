/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
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
 * @author zarpy
 */
@Path("person")
public class PersonResource {

     @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNewPerson(String PersonJson){
        PersonDTO newPerson = GSON.fromJson(PersonJson, PersonDTO.class);
        
        PersonDTO addedPerson = FACADE.addPerson(newPerson);
        
        
        return new Gson().toJson(addedPerson);
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
        return new Gson().toJson(deleted);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("allperson")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersons() {
        
        return new Gson().toJson(FACADE.getAllPersons().getList());
    }
    
    
}
