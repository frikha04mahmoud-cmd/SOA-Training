package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class BusniessRestAPI {

    static ModuleBusiness helper = new ModuleBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListModules() {
        return Response
                .status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {
            return Response
                    .status(200)
                    .entity(module)
                    .build();
        } else {
            return Response.status(404).entity("error").build();
        }
    }

    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@QueryParam("type") Module.TypeModule type) {
        return Response
                .status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }

    @GET
    @Path("/ue/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("code") int code) {
        UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();
        UniteEnseignement ue = ueHelper.getUEByCode(code);

        if (ue != null) {
            return Response
                    .status(200)
                    .entity(helper.getModulesByUE(ue))
                    .build();
        } else {
            return Response.status(404).entity("error").build();
        }
    }

    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(404).entity("error").build();
        }
    }

    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(404).entity("error").build();
        }
    }
}