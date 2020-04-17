package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.covid19Object;
import edu.upc.eetac.dsa.models.brote;
import org.apache.log4j.Logger;

import java.util.*;

public class covid19ManagerImpl implements covid19Manager {

    private static covid19Manager instance;
    private HashMap<String, brote> mapUser;
    private List<covid19Object> listGameObjects;
    private static Logger log = Logger.getLogger(covid19ManagerImpl.class);

    //Private Constructor
    covid19ManagerImpl(String s, String covid, String s1, String s2, String s3, int i, String virus, String m, String s4, String xcx, String s5) {
        //Singleton Private Constructor
        this.mapUser = new HashMap<>();
        this.listGameObjects = new LinkedList<>();
    }

    //Singleton implementation for the instance of the GameManager
    public static covid19Manager getInstance() {
        if (instance == null) {
            instance = new covid19ManagerImpl("001", "covid", "1", "23041998", "23051998", 1, "virus", "m", "111", "xcx", "3");
        }
        return instance;
    }



    //Añadir brote
    @Override
    public int addBrote(String id, String name, String surname, String birthDate, String reportDate, int riskLevel, String gender, String email, String telephone, String direction, String classification) {
        brote tmp_user = new brote(id, name, surname, birthDate, reportDate, riskLevel, gender, email, telephone, direction, classification);
        try {
            mapUser.put(id, tmp_user);
            log.info("Outbreak Added: " + tmp_user);
            return 201; //OK CREATED
        } catch (IndexOutOfBoundsException e) {
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        } catch (IllegalArgumentException e) {
            log.error("Incorrect format exception");
            return 400; //BAD REQUEST
        }
    }

    //Modificar brote
    @Override
    public int updateBrote(String id, String name, String surname, String birthDate, String reportDate, int riskLevel, String gender, String email, String telephone, String direction, String classification) {
        brote upd_usr = this.mapUser.get(id);
        if (upd_usr != null) {
            try {
                upd_usr.setName(name);
                upd_usr.setSurname(surname);
                upd_usr.setBirthDate(birthDate);
                upd_usr.setReportDate(reportDate);
                upd_usr.setRiskLevel(riskLevel);
                upd_usr.setGender(gender);
                upd_usr.setEmail(email);
                upd_usr.setTelephone(telephone);
                upd_usr.setDirection(direction);
                upd_usr.setClassification(classification);
                log.info("Updated outbreak parameters:" + upd_usr);
                return 201; //OK CREATED
            } catch (IllegalArgumentException e) {
                log.error("An incorrect format for the outbreak");
                return 400; //BAD REQUEST
            }
        } else {
            return 404; //User Not Found
        }
    }

    //Consultar brote
    @Override
    public brote getBrote(String id) {
        brote upd_usr = this.mapUser.get(id);
        if (upd_usr != null) {
            log.info("Outbreak found: " + upd_usr);
        } else {
            log.error("Outbreak not found for ID: " + id);
        }
        return upd_usr;
    }

    //Consultar numeros de usuarios que hay en el sistema
    @Override
    public int numBrotes() {
        return this.mapUser.size();
    }

    @Override
    public int addBroteCovid19Object(String id, String covid19ObjectId) {
        return 0;
    }

    @Override
    public int addBroteCovid19(String id, List<covid19Object> listCovid19Objects) {
        return 0;
    }

    @Override
    public int getNumCovid19ObjectBrotes(String id) {
        return 0;
    }

    @Override
    public int addCovid19Object(covid19Object covid19Object) {
        return 0;
    }

    @Override
    public int addCovid19Objects(List<covid19Manager> listCovid19Objects) {
        return 0;
    }

    @Override
    public covid19Object getCovid19Object(String ObjectId) {
        return null;
    }

    @Override
    public List<brote> getBroteList() {
        return null;
    }

    @Override
    public void liberateReserves() {

    }

    @Override
    public String generateId() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
    //Añadir Objeto sobre un usuario
   /* @Override
    public int addBroteCovid19Object(String broteId, String covid19Id) {
        brote temp_usr = mapUser.get(broteId);
        //From Object Id to Object from the List of Objects
        GameObject temp_covid19Object = getGameObject( covid19Id);
        if(temp_usr != null && temp_covid19Object!=null){
            int err = temp_usr.setGameObject(temp_covid19Object);
            if(err == 201){
                log.info("Object added to user " + temp_usr.getName() + " : " + temp_covid19Object.getName());
                return 201; //OK CREATED
            }
            else if(err == 400){
                log.error("Bad Format");
                return 400; //BAD REQUEST
            }
            else{
                log.error("No Inventory space for user: "+ temp_usr.getName());
                return 507; //INSUFFICIENT STORAGE
            }
        }
        else{
            log.error("User: "+broteId +" &/or Object: " + covid19Id +" NOT FOUND!");
            return 404; //USER NOT FOUND
        }
    }


    }

    //Añadir Lista de Objetos sobre un usuario
    @Override
    public int addUserGameObjects(String id, List<covid19Object> listGameObjects){
        brote temp_usr = mapUser.get(id);
        if(temp_usr != null){
            int err = temp_usr.setListGameObjects_resCode(listGameObjects);
            if(err == 201){
                log.info("201: Object List added to user " + temp_usr.getName());
                return 201; //OK CREATED
            }
            else if(err == 400){
                log.error("400: Bad Format");
                return 400; //BAD REQUEST
            }
            else{
                log.error("204: No Object Content: "+ temp_usr.getName());
                return 204; //204 No Content
            }
        }
        else{
            log.error("User Not found");
            return 404; //USER NOT FOUND
        }
    }
    //Consultar el número de objetos de un sistema
    @Override
    public int getNumCovid19ObjectBrotes(String id) {
        brote temp_usr = mapUser.get(id);
        if(temp_usr != null){
            log.info("Outbreak: "+temp_usr.getName() + " has : "+temp_usr.getNumCovid19Objects());
            return temp_usr.getNumCovid19Objects();
        }
        else{
            log.error("User Not found");
            return 404; //USER NOT FOUND
        }
    }

    @Override
    public int addGameObject(covid19Object covid19Object) {
        return 0;
    }

    @Override
    public int addGameObjects(List<covid19Manager> listCovid19Objects) {
        return 0;
    }

    /*                          EXTRAS                  */
    /*@Override
    public int addGameObject(GameObject gameObject) {
        int result;
        try {
            this.listGameObjects.add(gameObject);
            log.info("201: Object Added: " + gameObject.getName());
            result = 201;//OK CREATED
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    @Override
    public int addGameObjects(List<GameObject> listGameObjects) {
        int result;
        try {
            this.listGameObjects.addAll(listGameObjects);
            log.info("201: Objects Added: " + listGameObjects.toString());
            result = 200;//OK Added
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    @Override
    public GameObject getGameObject(String gameObjectId) {
        GameObject result_Game_object = null;
        try{
            for(GameObject gameObject : this.listGameObjects){
                if (gameObject.getId().compareTo(gameObjectId) == 0){
                    result_Game_object = gameObject;
                    log.info("302: Object found: " + gameObject.getName());
                }
            }
        }catch(ExceptionInInitializerError e){
            log.error("400: Object list not initialized");
            return null; //400 ERROR List of Objects not initialized
        }
        return result_Game_object;
    }
    @Override
    public List<brote> getUsersList() {
        List<brote> result = null;
        if(this.mapUser.size() !=0){
            result = new LinkedList<>(this.mapUser.values());
            log.info("User List: "+result.toString());
        }
        return result; //Null: 404 Empty User HashMap
    }
    //Generate Id
    @Override
    public String generateId(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random generated ID
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    //Liberar Recursos
    @Override
    public void liberateReserves() {
        this.listGameObjects.clear();
        this.mapUser.clear();
    }
}*/
