/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.HobbyFacade;
import facades.PersonFacade;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author magda
 */
public class HobbyResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    private static PersonFacade personFacade;
    private static HobbyFacade hobbyFacade;
    /*  private Phone phone1 = new Phone(123123123, "private");
    private Phone phone2 = new Phone(22222222, "home");
    private Phone phone3 = new Phone(333333, "mobil");
    private CityInfo cityInfo1= new CityInfo("1234");
    private CityInfo cityInfo2= new CityInfo("5678");
    private Address address1 = new Address("Street One", "2.th",cityInfo1);
    private Address address2 = new Address("Street Two", "3.th", cityInfo2);
   // private Person person1;
    //private Person person2;
     */
    private Hobby hobby1 = new Hobby("D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
    private Hobby hobby2 = new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
    private Hobby hobby3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");

    private String hobbyName1 = "D-udskrivning";
    private String hobbyName2 = "Akrobatik";
    private String hobbyName3 = "Skuespil";

    @BeforeAll
    public static void setUpClass() throws IOException {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        httpServer.start();
        while (!httpServer.isStarted()) {
        }
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;

        personFacade = PersonFacade.getPersonFacade(emf);
        EntityManager em = emf.createEntityManager();
        Hobby hobby1 = new Hobby("D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
        Hobby hobby2 = new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
        Hobby hobby3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");
        CityInfo cityInfo1 = new CityInfo("1234");
        CityInfo cityInfo2 = new CityInfo("5678");
        try {
            
            em.getTransaction().begin();
             em.createQuery("DELETE FROM Phone").executeUpdate();            
            em.createQuery("DELETE FROM Address").executeUpdate();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM CityInfo").executeUpdate();            
            em.createQuery("DELETE FROM Hobby").executeUpdate();
            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(cityInfo1);
            em.persist(cityInfo2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            Phone phone1 = new Phone(123123123, "private");
            Phone phone2 = new Phone(22222222, "home");
            Phone phone3 = new Phone(333333, "mobil");
            CityInfo cityInfo1 = em.find(CityInfo.class, "1234");
            CityInfo cityInfo2 = em.find(CityInfo.class, "5678");
            Address address1 = new Address("Street One", "2.th", cityInfo1);
            Address address2 = new Address("Street Two", "3.th", cityInfo2);
            em.getTransaction().begin();

            em.createQuery("DELETE FROM Phone").executeUpdate();            
            em.createQuery("DELETE FROM Address").executeUpdate();
            em.createQuery("DELETE FROM Person").executeUpdate();
            
            Person person1 = new Person("email@email", "Magda", "Wawrzak");
            person1.addHobby(em.find(Hobby.class, hobbyName1));

            person1.addAddress(address1);
            System.out.println("person1 " + person1.getPhoneNumbers().size());
            person1.addPhone(phone1);
            person1.addPhone(phone2);

            Person person2 = new Person("email@com", "Bob", "Sponge");
            person2.addHobby(em.find(Hobby.class, hobbyName1));
            person2.addHobby(em.find(Hobby.class, hobbyName2));
            person2.addPhone(phone3);
            person2.addAddress(address2);

            em.persist(person1);
            em.persist(person2);
            Hobby hobby = em.find(Hobby.class, hobbyName1);
            System.out.println("hobby " + hobby.getName() + " has " + hobby.getPersonList().size() + " perosns");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Phone").executeUpdate();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM Address").executeUpdate();
            em.createQuery("DELETE FROM CityInfo").executeUpdate();
            em.getTransaction().commit();

        } finally {

            em.close();
        }
    }

    @AfterAll
    public static void closeTestServer() {

        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given()
                .when()
                .get("/hobby")
                .then()
                .statusCode(200);
    }

    //g
    @Test
    public void testGetAllPersonsByHobby() throws Exception {
        given()
                .contentType("application/json")
                .get("/hobby/personlist/" + hobbyName1).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("firstName", hasItems("Magda", "Bob"));
    }

}
