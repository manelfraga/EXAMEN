package edu.upc.eetac.dsa;
import edu.upc.eetac.dsa.models.brote;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class covid19ManagerImplTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static Logger logger = Logger.getLogger(covid19ManagerImplTest.class);
    //GameManager
    public covid19Manager manager = null;
    //Estructura de datos
    brote outbreak;
    List<covid19Manager> listCovid19Outbreaks;
    //Metodo SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        //Instancing GameManager Implementation
        manager = covid19ManagerImpl.getInstance();
       //Initializing Object List
        listCovid19Outbreaks =  new LinkedList<covid19Manager>();
        //Initialzing Test User
        outbreak = new brote("111","manel","f","12032020","13032020",1,"virus","m@gmail.com","111222333","laCasa","1");
        //Appending data to Object List
        listCovid19Outbreaks.add(new covid19ManagerImpl("001", "covid","1","23041998","23051998",1,"virus","m","111","xcx","3"));
        listCovid19Outbreaks.add(new covid19ManagerImpl("002", "covid","2","23041998","23051998",1,"virus","m","111","xcx","3"));
        listCovid19Outbreaks.add(new covid19ManagerImpl("003", "covid","3","23041998","23051998",1,"virus","m","111","xcx","3"));
        //Adding Objects list to Game Manager
        manager.addCovid19Objects(listCovid19Outbreaks);
    }
    //Tests
    //Metodo Test para añadir un usuario en ek sistema y verificar el número de usuarios
    @Test
    public void addUserTest(){
        //Initial Test, initial users in game Zero!
        Assert.assertEquals(0, this.manager.numBrotes());
        //Adding a user to the GameManager
        manager.addBrote(outbreak.getId(), outbreak.getName(), outbreak.getSurname(), outbreak.getBirthDate(), outbreak.getReportDate(), outbreak.getRiskLevel(), outbreak.getGender(), outbreak.getEmail(), outbreak.getTelephone(), outbreak.getDirection(), outbreak.getClassification());
        Assert.assertEquals(1, manager.numBrotes());
        //Adding a second user to the GameManager
        manager.addBrote("abc","Manel","Fraga", "12/03/2020","13/03/2020", 1,"virus", "manel.etc.etc@gmail.com", "111222333", "miCasa", "1");
        Assert.assertEquals(2, manager.numBrotes());
    }

    @Test
    public void addObjectTest(){
        //Setting up with 1 Test User
        manager.addBrote(outbreak.getId(), outbreak.getName(), outbreak.getSurname(), outbreak.getBirthDate(), outbreak.getReportDate(), outbreak.getRiskLevel(), outbreak.getGender(), outbreak.getEmail(), outbreak.getTelephone(), outbreak.getDirection(), outbreak.getClassification());
        //Test for the objects the test user has equals 0 as setUp method
        Assert.assertEquals(0, manager.getNumCovid19ObjectBrotes(outbreak.getId()));
        //Adding an object to the User passing a id of the Object, Expects http 201 Ok
        Assert.assertEquals(0,manager.addBroteCovid19Object(outbreak.getId(), listCovid19Outbreaks.get(0).getId()));
        //Test if the number of objects inside Test User has increased to 1
        Assert.assertEquals(0, manager.getNumCovid19ObjectBrotes(outbreak.getId()));
    }

    //Metodo Teardown
    @After
    public void tearDown() {
        manager.liberateReserves();
    }
}