package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestAPI {

    // helper: instance that will help this class to manipulate data (CRUD)
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    // get list UEs
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUEs() {
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }

    // add a new UE
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUe(UniteEnseignement unite) {
        if (helper.addUniteEnseignement(unite)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }

    // get UE by code
    @GET
    @Path("/{inputCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("inputCode") int code) {
        return Response
                .status(200)
                .entity(helper.getUEByCode(code))
                .build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@QueryParam("semestre") int semestre) {
        return Response.ok()
                .entity(helper.getUEBySemestre(semestre))
                .build();
    }

    @DELETE
    @Path("/delete/{code}")
    public Response deleteUeByCode(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(404).entity("error").build();
        }
    }
}