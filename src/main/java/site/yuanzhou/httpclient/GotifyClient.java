package site.yuanzhou.httpclient;


import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import site.yuanzhou.entity.GotifyDTO;

@RegisterRestClient(configKey = "gotify-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GotifyClient {


    @POST
    @Path("/message")
    Uni<String> sendNotice(GotifyDTO noticeDTO, @QueryParam("token") String appToken);
}
