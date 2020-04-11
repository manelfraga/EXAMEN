package edu.upc.eetac.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    //Basic User Values
    private String id;
    private String name;
    private String surname;
    //Game User Objects
    private List<GameObject> listGameObjects = null; //List of Objects
    //Public Constructor to initialize User
    public User(String id, String name, String surname){
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.listGameObjects = new LinkedList<GameObject>();
    }
    public User(){
        //Empty Constructor Initialization for second cases
        //Objects list of User is always initialized empty
    }

    public int getNumGameObjects(){
        return this.listGameObjects.size();
    }

    //Returns an object from the list, else null for Out of bounds or Not initialized
    public GameObject getGameObject(int index){
        try {
            return this.listGameObjects.get(index);
        }
        catch (IndexOutOfBoundsException | ExceptionInInitializerError e ){
            return null;
        }
    }
    //Adds Object to the User list
    public int setGameObject(GameObject gameObject){
        try{
            this.listGameObjects.add(gameObject);
        }
        catch (ExceptionInInitializerError e)
        {
            return 400;//400 Bad Request
        }
        catch (IndexOutOfBoundsException e){
            return 507;//Insufficient storage
        }
        return 201;//201 Created
    }
    //Returns User Object List
    public List<GameObject> getListGameObjects(){
        return this.listGameObjects;
    }
    //Adds a List of Objects to User
    public int setListObjects(List<GameObject> listGameObjects) {
        try{
            this.listGameObjects.addAll(listGameObjects);
        }
        catch(NullPointerException e){
            return 204;//204 No Content
        }
        catch( IndexOutOfBoundsException e){
            return 400;//400 Bad Request
        }
        return 201;//201 Created
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    //Returns in string format the user
    @Override
    public String toString(){
        return "ID: " + this.getId() + " | Name: " + this.getName() + " | Surname: " + this.getSurname() ;
    }
}
