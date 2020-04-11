package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.GameManager;
import edu.upc.eetac.dsa.GameManagerImpl;
import edu.upc.eetac.dsa.models.GameObject;
import edu.upc.eetac.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

//Models or Element Entity
//Swagger Imports
@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UsersService {
    static final Logger logger = Logger.getLogger(UsersService.class);
    private GameManager manager;
    public UsersService(){
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = GameManagerImpl.getInstance();
        if (this.manager.numUsers() == 0) {
            //Adding Users
            this.manager.addUser("001","Midoriya","Izuku");
            this.manager.addUser("002","Bakugo","Katsuki");
            this.manager.addUser("003","Uraraka","Ochaco");
            //Adding GameObjects
            this.manager.addGameObject(new GameObject("01","Sword"));
            this.manager.addGameObject(new GameObject("02","Shield"));
            this.manager.addGameObject(new GameObject("03","Potion"));
            //Adding objects to users
            //Sword & Shield to Midoriya
            this.manager.addUserGameObject("001","01");
            this.manager.addUserGameObject("001","02");
            //Only shield for Bakugo
            this.manager.addUserGameObject("002","02");
            //Only Potion for uraraka
            this.manager.addUserGameObject("003","03");
        }
    }
    //When multiple GET, PUT, POSTS & DELETE EXIST on the same SERVICE, path must be aggregated
    //Lista de usuarios
    @GET
    @ApiOperation(value = "Get all Users", notes = "Retrieves the list of Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> user = this.manager.getUsersList();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(user) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Añadir un usuario
    //Adds a new user given multiple parameters(name & surname)
    @POST
    @ApiOperation(value = "create a new User", notes = "Adds a new user given name and surname")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addUser/{name}/{surname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("name") String name,@PathParam("surname") String surname ) {
        if (name.isEmpty() || surname.isEmpty())  return Response.status(500).entity(new User()).build();
        String temp_id = manager.generateId();
        this.manager.addUser(temp_id,name,surname);
        return Response.status(201).entity(manager.getUser(temp_id)).build();
    }
    //Modificar un usuario
    @PUT
    @ApiOperation(value = "Update a User", notes = "Edits an existing User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/modifyUser/{id}/{name}/{surname}")
    public Response updateUser(@PathParam("id") String id,@PathParam("name") String name,@PathParam("surname") String surname ) {

        int resp = this.manager.updateUser(id,name,surname);
        if (resp != 201) return Response.status(resp).build();
        return Response.status(201).entity(manager.getUser(id)).build();
    }

    //Consultar un Usuario
    @GET
    @ApiOperation(value = "Get a User", notes = "Retrieve User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/consultUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User user = this.manager.getUser(id);
        if (user == null) return Response.status(404).build();
        else  return Response.status(201).entity(user).build();
    }
    //Consultar objetos de un usuario
    @GET
    @ApiOperation(value = "Get a User GameObjects", notes = "Retrieve User Game Objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = GameObject.class,responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 204, message = "No Game Object found")
    })
    @Path("/consultGameObjectsUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserObject(@PathParam("id") String id) {
        User user = this.manager.getUser(id);
        List<GameObject> listGameObject;
        if (user == null) return Response.status(404).build();
        else {
            if(user.getNumGameObjects()==0){
                Response.status(204).build();
            }
        }
        listGameObject = user.getListGameObjects();
        GenericEntity<List<GameObject>> entity = new GenericEntity<List<GameObject>>(listGameObject) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Añadir un objeto sobre un usuario
    //Adds a new object given multiple parameters(userId & gameObjectId)
    @PUT
    @ApiOperation(value = "Adds a Game object to user", notes = "Adds an existing Game Object to user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User/GameObject Not found Error")
    })
    @Path("/addGameObjectUser/{userId}/{gameObjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObject(@PathParam("userId") String userId,@PathParam("gameObjectId") String gameObjectId ) {
        if (userId.isEmpty() || gameObjectId.isEmpty())  return Response.status(500).entity(new User()).build();
        else{
            User user = manager.getUser(userId);
            GameObject gameObject = manager.getGameObject(gameObjectId);
            if(user==null || gameObject ==null)  return Response.status(404).entity(new User()).build();
        }
        manager.addUserGameObject(userId,gameObjectId);
        return Response.status(201).entity(manager.getUser(userId)).build();
    }

}
