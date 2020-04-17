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
