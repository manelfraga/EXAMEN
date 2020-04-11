package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.GameObject;
import edu.upc.eetac.dsa.models.User;

import java.util.List;
public interface GameManager {
    //listado de usuarios ordenado alfaticamente
    List<User> getSortedUsersAlphabetical();
    //Añadir un usuario
    int addUser(String id, String name, String surname);
    //Modicar un usuario
    int updateUser(String id, String name, String surname);
    //Consultar información de un usuario
    User getUser(String id);
    //Consultar número de un usuarios que hay en el sistema
    int numUsers();
    //Consultar los objetos de un usuario (orden de inserción)
    int addUserGameObject(String id, String gameObjectId);
    //Consultar los objetos de un usuario (orden de inserción)
    int addUserGameObjects(String id, List<GameObject> listGameObjects);
    //Consultar el número de objetos de un usuario
    int getNumGameObjectsUser(String id);
    /*                  Extras                            */
    //Añadir un Objeto
    int addGameObject(GameObject gameObject);
    //Añadir una lista de Objetos
    int addGameObjects(List<GameObject> listGameObjects);
    //Consultar un Objeto
    GameObject getGameObject(String ObjectId);
    //Consultar la lista de Objetos
    List<User> getUsersList();
    //Liberar Recursos
    void liberateReserves();
    //Generate Id
    String generateId();
}
