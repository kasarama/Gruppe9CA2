/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonListDTO;
import facades.HobbyFacade;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("hobby")
public class HobbyResource {

    @Context
    private UriInfo context;
    
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HobbyFacade FACADE = HobbyFacade.getHobbyFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hobby Resources\"}";
    }

    @Path("personlist/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsByHobby(@PathParam("name") String name) {
        PersonListDTO list = FACADE.getPersonList(name);
        return new Gson().toJson(list.getList());
    }

  
}
