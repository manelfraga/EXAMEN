package edu.upc.eetac.dsa;
import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.Object;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
//Junit 4.13
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GameManagerImplTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static Logger logger = Logger.getLogger(GameManagerImplTest.class);
    //GameManager
    public GameManagerImpl manager = null;
    //Estructura de datos
    User user;
    List<Object> listObjects;
    //Metodo SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        //Configure logger
        BasicConfigurator.configure();
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message");
        logger.warn("warning  Test Message!");
        logger.error("error Test Message");
        manager = GameManagerImpl.getInstance();
       //Initializing Object List
        listObjects =  new LinkedList<Object>();
        //Initialzing Test User
        user = new User("xyz", "Krunal", "Badsiwal");
        //Appending data to Object List
        listObjects.add(new Object("001", "Sword"));
        listObjects.add(new Object("002", "Shield"));
        listObjects.add(new Object("003", "Potion"));
        //Adding Objects list to Game Manager
        manager.addObjects(listObjects);
    }
    //Tests
    //Metodo Test para añadir un usuario en ek sistema y verificar el número de usuarios
    @Test
    public void addUserTest(){
        //Initial Test, initial users in game Zero!
        Assert.assertEquals(0, this.manager.numUsers());
        //Adding a user to the GameManager
        manager.addUser(user.getId(),user.getName(),user.getSurname());
        Assert.assertEquals(1, manager.numUsers());
        //Adding a second user to the GameManager
        manager.addUser("abc","Toni","Oller");
        Assert.assertEquals(2, manager.numUsers());
    }

    @Test
    public void addObjectTest(){
        //Setting up with 1 Test User
        manager.addUser(user.getId(),user.getName(),user.getSurname());
        //Test for the objects the test user has equals 0 as setUp method
        Assert.assertEquals(0, manager.getNumObjectsUser(user.getId()));
        //Adding an object to the User passing a id of the Object, Expects http 201 Ok
        Assert.assertEquals(201,manager.addUserObject(user.getId(),listObjects.get(0).getId()));
        //Test if the number of objects inside Test User has increased to 1
        Assert.assertEquals(1, manager.getNumObjectsUser(user.getId()));
    }

    //Metodo Teardown
    @After
    public void tearDown() {
        manager.liberateReserves();
    }
}