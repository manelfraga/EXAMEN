package edu.upc.eetac.dsa.models;

public class covid19Object {

    private  String name;
    private  String id;
    //Can contain multiple constructors to initialize itself
    public covid19Object (String id, String name){
        this.name = name;
        this.id = id;
    }
    //Models must always contain empty constructor and setter and getters for API Rest Magic!
    public covid19Object(){
    }
    public void setName(String name){this.name =name;}
    public void setId(String id){this.id =id;}
    public String getName() {
        return this.name;
    }
    public String getId() {return id;}
    @Override
    public String toString(){
        return "ID: " + this.getId() + " | Name: " + this.getName() ;
    }
}

