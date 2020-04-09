package edu.upc.eetac.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    //Basic User Values
    private String id;
    private String name;
    private String surname;
    //Game User Objects
    private List<Object> listObjects = null; //List of Objects
    private int numObjects = 0; //Number of Objects in the list

    //Public Constructor to initialize User
    public User(String id, String name, String surname){
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.listObjects = new LinkedList<Object>();
        numObjects = 0;
    }
    public User(){
        //Empty Constructor Initialization for second cases
        //Objects list of User is always initialized empty
        this.listObjects = new LinkedList<Object>();
        numObjects = 0;
    }

    public void setNumObjects(int numObjects){
        this.numObjects = numObjects;
    }
    public int getNumObjects(){
        return this.numObjects;
    }

    //Returns an object from the list, else null for Out of bounds or Not initialized
    public Object getObject(int index){
        try {
            return this.listObjects.get(index);
        }
        catch (IndexOutOfBoundsException | ExceptionInInitializerError e ){
            return null;
        }
    }
    //Adds Object to the User list
    public int setObject(Object object){
        try{
            this.listObjects.add(object);
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
    public List<Object> getListObjects(){
        return this.listObjects;
    }
    //Adds a List of Objects to User
    public int setListObjects(List<Object> listObjects) {
        try{
            this.listObjects.addAll(listObjects);
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
