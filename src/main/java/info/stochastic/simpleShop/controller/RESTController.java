package info.stochastic.simpleShop.controller;

import info.stochastic.simpleShop.ejb.ApplicationService;
import info.stochastic.simpleShop.ejb.Clothes;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Named("RESTController")
@Path("/")
@SessionScoped
@SuppressWarnings("serial")
public class RESTController implements Serializable {

    @EJB
    private ApplicationService appService;

    @POST
    @Path("/shop")
    @Consumes({"application/json"})
    public Response addToShop(Clothes clothes) {
        System.out.println("cur id = " + clothes.getId());
    	appService.addToShop(clothes);
        return Response.ok().build();
    }
    
    @GET
    @Path("/shop/{id}")
    @Produces({"application/json"})
    public Clothes getClothesFromShopById(@PathParam("id") int id) {
        return appService.getFromShopById(id);
    }
    
    @POST
    @Path("/warehouse")
    public Response addToWarehouse(Clothes clothes) {
        System.out.println("cur id = " + clothes.getId());
    	appService.addToWarehouse(clothes);
        return Response.ok().build();
    }
    
    @GET
    @Path("/warehouse/{id}")
    @Produces({"application/json"})
    public Clothes addToShop(@PathParam("id") int id) {
        return appService.getFromWarehouseById(id);
    }
    
    @GET
    @Path("/warehouse")
    @Produces({"application/json"})
    public List<Clothes> getAllFromWarehouse() {
    	return appService.getAllFromWarehouse();
    }
    
    @GET
    @Path("/shop")
    @Produces({"application/json"})
    public List<Clothes> getAllFromShop() {
    	return appService.getAllFromShop();
    }
    
    @DELETE
    @Path("/warehouse/{id}")
    @Consumes({"application/json"})
    public Response delFromWarehouse(@PathParam("id") int id) {
		appService.removeFromWarehouse(appService.getFromWarehouseById(id));
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/shop/{id}")
    @Consumes({"application/json"})
    public Response delFromShop(@PathParam("id") int id) {
		appService.removeFromShop(appService.getFromShopById(id));
        return Response.ok().build();
    }
    
    @PUT
    @Path("/shopTowarehouse/{id}")
    @Consumes({"application/json"})
    public Response fromShopToWarehouse(Clothes clothes) {
    	Clothes oldClothes = appService.getFromShopById(clothes.getId());
		appService.removeFromShop(oldClothes);
		appService.addToWarehouse(clothes);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/warehouseToshop/{id}")
    @Produces({"application/json"})
    public Response fromWarehouseToShop(Clothes clothes) {
    	Clothes oldClothes = appService.getFromWarehouseById(clothes.getId());
		appService.removeFromWarehouse(oldClothes);
		appService.addToShop(clothes);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/shop/{id}")
    @Produces({"application/json"})
    public Response updateShop(Clothes clothes) {
    	Clothes oldClothes = appService.getFromShopById(clothes.getId());
		appService.removeFromShop(oldClothes);
		appService.addToShop(clothes);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/warehouse/{id}")
    @Produces({"application/json"})
    public Response updateWarehouse(Clothes clothes) {
    	Clothes oldClothes = appService.getFromWarehouseById(clothes.getId());
		appService.removeFromWarehouse(oldClothes);
		appService.addToWarehouse(clothes);
        return Response.ok().build();
    }
}
