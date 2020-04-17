package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.covid19Object;
import edu.upc.eetac.dsa.models.brote;

import java.util.List;
public interface covid19Manager {


    //Añadir un brote
    int addBrote(String id, String name, String surname, String birthDate, String reportDate, int riskLevel, String gender, String email, String telephone, String direction, String classification);
    //Modicar un brote
    int updateBrote(String id, String name, String surname, String birthDate, String reportDate, int riskLevel, String gender, String email, String telephone, String direction, String classification);
    //Consultar información de un brote
    brote getBrote(String id);
    //Consultar número de un brotes que hay
    int numBrotes();

    int addBroteCovid19Object(String id, String covid19ObjectId);

    int addBroteCovid19(String id, List<covid19Object> listCovid19Objects);
    //Consultar el número de objetos de un usuario
    int getNumCovid19ObjectBrotes(String id);
    /*                  Extras                            */
    //Añadir un Objeto
    int addCovid19Object(covid19Object covid19Object);
    //Añadir una lista de Objetos
    int addCovid19Objects(List<covid19Manager> listCovid19Objects);
    //Consultar un Objeto
    covid19Object getCovid19Object(String ObjectId);
    //Consultar la lista de Objetos
    List<brote> getBroteList();
    //Liberar Recursos
    void liberateReserves();
    //Generate Id
    String generateId();

    String getId();
}
