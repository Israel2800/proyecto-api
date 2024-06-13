package fca.suayed.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import fca.suayed.dal.StoreDal;
import fca.suayed.dto.ClientDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clients")
public class ClientsResource {
    
    @Inject
    StoreDal storeDal;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all clients")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response getClients(){
        var responseDto = storeDal.getClients();
        return Response.ok(responseDto).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new client")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response registerUser(ClientDto clientDto) {
        var responseDto = storeDal.addClient(clientDto);
        return Response.ok(responseDto).build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a client by ID")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Client successfully deleted"),
        @APIResponse(responseCode = "404", description = "Client not found")
    })
    public Response deleteClient(@PathParam("id") int id) {
        var responseDto = storeDal.deleteClient(id);
        if (responseDto.getSuccess()) {
            return Response.ok(responseDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(responseDto).build();
        }
    }
}
