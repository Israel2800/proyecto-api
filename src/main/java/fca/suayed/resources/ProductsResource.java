package fca.suayed.resources;


import fca.suayed.dal.StoreDal;
import fca.suayed.dto.ProductDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;



@Path("/products")
public class ProductsResource {

    @Inject
    StoreDal storeDal;


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all products")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response getProducts() {

        var responseDto = storeDal.getProducts();
        return Response.ok(responseDto).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new product")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response registerUser(ProductDto productDto) {
        var responseDto = storeDal.addProduct(productDto);
        return Response.ok(responseDto).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a product by ID")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Product successfully deleted"),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    public Response deleteProduct(@PathParam("id") int id) {
        var responseDto = storeDal.deleteProduct(id);
        if (responseDto.getSuccess()) {
            return Response.ok(responseDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(responseDto).build();
        }
    }

}