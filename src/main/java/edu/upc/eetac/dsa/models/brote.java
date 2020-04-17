package edu.upc.eetac.dsa.models;
import java.util.LinkedList;
import java.util.List;

public class brote {
    //Basic User Values
    private String id;
    private String name;
    private String surname;
    private String birthDate;
    private String reportDate;
    private int riskLevel;
    private String gender;
    private String email;
    private String telephone;
    private String direction;
    private String classification;

    private List<covid19Object> listCovid19Object = null;

    public brote(String id, String name, String surname, String birthDate,String reportDate, int riskLevel, String gender,String email,String telephone,String direction, String classification) {
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.birthDate = birthDate;
        this.reportDate = reportDate;
        this.riskLevel = riskLevel;
        this.gender=gender;
        this.email=email;
        this.telephone=telephone;
        this.direction=direction;
        this.classification=classification;
        this.listCovid19Object = new LinkedList<>();
    }
    public brote(){

    }

    public int getNumCovid19Objects(){
        return this.listCovid19Object.size();
    }


    public covid19Object getCovid19Object(int index){
        try {
            return this.listCovid19Object.get(index);
        }
        catch (IndexOutOfBoundsException | ExceptionInInitializerError e ){
            return null;
        }
    }

    public int setCovid19Object(covid19Object covid19Object){
        try{
            this.listCovid19Object.add(covid19Object);
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

    public List<covid19Object> getListCovid19Object(){
        return this.listCovid19Object;
    }

    public void setListCovid19Object(List<covid19Object> listCovid19Object) {
        this.listCovid19Object = (listCovid19Object);
    }

    public int setListCovid19Object_resCode(List<covid19Object> listGameObjects) {
        try{
            this.listCovid19Object.addAll(listGameObjects);
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

    public String getBirthDate() { return birthDate; }

    public String getReportDate() {return reportDate; }

    public void setReportDate(String reportDate) {this.reportDate = reportDate; }

    public int getRiskLevel() {return riskLevel; }

    public void setRiskLevel(int riskLevel) {this.riskLevel = riskLevel; }

    public String getGender() {return gender; }

    public void setGender(String gender) {this.gender = gender; }

    public String getEmail() {return email; }

    public void setEmail(String email) {this.email = email; }

    public String getTelephone() {return telephone; }

    public void setTelephone(String telephone) {this.telephone = telephone; }

    public String getDirection() {return direction; }

    public void setDirection(String direction) {this.direction = direction; }

    public String getClassification() {return classification; }

    public void setClassification(String classification) {this.classification = classification; }

    public void setBirthDate(String birthDate) {this.birthDate = birthDate;

    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return "ID: " + this.getId() + " | Name: " + this.getName() + " | Surname: " + this.getSurname() ;
    }
}
