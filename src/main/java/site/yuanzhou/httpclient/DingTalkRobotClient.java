package site.yuanzhou.httpclient;


import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import site.yuanzhou.entity.DingTalkDTO;


@RegisterRestClient(configKey = "ding-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/robot")
public interface DingTalkRobotClient {

    @POST
    @Path("/send")
    Uni<String> sendNotice(DingTalkDTO noticeDTO, @QueryParam("access_token") String accessToken, @QueryParam("sign") String sign, @QueryParam("timestamp") Long timestamp);
}
