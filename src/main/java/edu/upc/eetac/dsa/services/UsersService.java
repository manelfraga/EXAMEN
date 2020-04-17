package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.covid19Manager;
import edu.upc.eetac.dsa.covid19ManagerImpl;
import edu.upc.eetac.dsa.models.covid19Object;
import edu.upc.eetac.dsa.models.brote;
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
import java.util.List;

//Models or Element Entity
//Swagger Imports
@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UsersService {
    static final Logger logger = Logger.getLogger(UsersService.class);
    private covid19Manager manager;
    public UsersService(){
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = covid19ManagerImpl.getInstance();
        if (this.manager.numBrotes() == 0) {
            //Adding Users
            this.manager.addBrote("001", "covid","1","23041998","23051998",1,"virus","m","111","xcx","3");
            this.manager.addBrote("001", "covid","1","23041998","23051998",1,"virus","m","111","xcx","3");
            this.manager.addBrote("001", "covid","1","23041998","23051998",1,"virus","m","111","xcx","3");
            //Adding GameObjects
            this.manager.addCovid19Object(new covid19Object("01","gripe"));
            this.manager.addCovid19Object(new covid19Object("02","malaria"));
            this.manager.addCovid19Object(new covid19Object("03","gastrointeritis"));
            //Adding objects to users
            //Sword & Shield to Midoriya
            this.manager.addBroteCovid19Object("001","01");
            this.manager.addBroteCovid19Object("001","02");
            //Only shield for Bakugo
            this.manager.addBroteCovid19Object("002","02");
            //Only Potion for uraraka
            this.manager.addBroteCovid19Object("003","03");
        }
    }
    //When multiple GET, PUT, POSTS & DELETE EXIST on the same SERVICE, path must be aggregated
    //Lista de usuarios
    @GET
    @ApiOperation(value = "Get all Users", notes = "Retrieves the list of Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = brote.class, responseContainer="List"),
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<brote> user = this.manager.getBroteList();

        GenericEntity<List<brote>> entity = new GenericEntity<List<brote>>(user) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Añadir un usuario
    //Adds a new user given multiple parameters(name & surname)
    @POST
    @ApiOperation(value = "create a new User", notes = "Adds a new user given name and surname")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= brote.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addUser/{name}/{surname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("name") String name,@PathParam("surname") String surname,@PathParam("birthDate") String brithDate,@PathParam("reportDate") String reportDate,@PathParam("riskLevel") int riskLevel,@PathParam("gender") String gender,@PathParam("email") String email, @PathParam("telephone") String telephone, @PathParam("direction") String direction, @PathParam("classification") String classification) {
        if (name.isEmpty() || surname.isEmpty())  return Response.status(500).entity(new brote()).build();
        String temp_id = manager.generateId();
        this.manager.addBrote(temp_id,name,surname,brithDate,reportDate,riskLevel,gender,email,telephone,direction,classification);
        return Response.status(201).entity(manager.getBrote(temp_id)).build();
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
    public Response updateUser(@PathParam("id") String id,@PathParam("name") String name,@PathParam("surname") String surname,@PathParam("birthDate") String brithDate,@PathParam("reportDate") String reportDate,@PathParam("riskLevel") int riskLevel,@PathParam("gender") String gender,@PathParam("email") String email, @PathParam("telephone") String telephone, @PathParam("direction") String direction, @PathParam("classification") String classification) {

        int resp = this.manager.updateBrote(id,name,surname,brithDate,reportDate,riskLevel,gender,email,telephone,direction,classification);
        if (resp != 201) return Response.status(resp).build();
        return Response.status(201).entity(manager.getBrote(id)).build();
    }

    //Consultar un Usuario
    @GET
    @ApiOperation(value = "Get a User", notes = "Retrieve User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = brote.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/consultUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        brote user = this.manager.getBrote(id);
        if (user == null) return Response.status(404).build();
        else  return Response.status(201).entity(user).build();
    }
    //Consultar objetos de un usuario
    @GET
    @ApiOperation(value = "Get a User GameObjects", notes = "Retrieve User Game Objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = covid19Object.class,responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 204, message = "No Game Object found")
    })
    @Path("/consultGameObjectsUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserObject(@PathParam("id") String id) {
        brote user = this.manager.getBrote(id);
        List<covid19Object> listGameObject;
        if (user == null) return Response.status(404).build();
        else {
            if(user.getNumCovid19Objects()==0){
                Response.status(204).build();
            }
        }
        listGameObject = user.getListCovid19Object();
        GenericEntity<List<covid19Object>> entity = new GenericEntity<List<covid19Object>>(listGameObject) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Añadir un objeto sobre un usuario
    //Adds a new object given multiple parameters(userId & gameObjectId)
    @PUT
    @ApiOperation(value = "Adds a Game object to user", notes = "Adds an existing Game Object to user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= brote.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User/GameObject Not found Error")
    })
    @Path("/addGameObjectUser/{userId}/{gameObjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObject(@PathParam("userId") String userId,@PathParam("gameObjectId") String gameObjectId ) {
        if (userId.isEmpty() || gameObjectId.isEmpty())  return Response.status(500).entity(new brote()).build();
        else{
            brote user = manager.getBrote(userId);
            covid19Object gameObject = manager.getCovid19Object(gameObjectId);
            if(user==null || gameObject ==null)  return Response.status(404).entity(new brote()).build();
        }
        manager.addBroteCovid19Object(userId,gameObjectId);
        return Response.status(201).entity(manager.getBrote(userId)).build();
    }

}
