package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.Object;
import java.util.List;
public interface GameManager {
    //listado de usuarios ordenado alfaticamente
    public List<User> getSortedUsersAlphabetical();
    //Añadir un usuario
    public int addUser(String id,String name, String surname);
    //Modicar un usuario
    public int updateUser(String id, String name, String surname);
    //Consultar información de un usuario
    public User getUser(String id);
    //Consultar número de un usuarios que hay en el sistema
    public int numUsers();
    //Consultar los objetos de un usuario (orden de inserción)
    public int addUserObject(String id, String objectId);
    //Consultar los objetos de un usuario (orden de inserción)
    public int addUserObjects(String id, List<Object> listObjects);
    //Consultar el número de objetos de un usuario
    public int getNumObjectsUser(String id);
    /*                  Extras                            */
    //Añadir un Objeto
    public int addObject(Object object);
    //Añadir una lista de Objetos
    public int addObjects(List<Object> listObjects);
    //Consultar un Objeto
    public Object getObject(String ObjectId);
    //Consultar la lista de Objetos
    public List<User> getUsersList();
    //Liberar Recursos
    public void liberateReserves();
}
