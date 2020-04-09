package edu.upc.eetac.dsa.models;

public class Object {
    private final String name;
    private final String id;

    public Object(String name, String id){
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public String getId() {return id;}
}
