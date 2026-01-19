package site.yuanzhou.entry;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.yuanzhou.entity.GotifyDTO;
import site.yuanzhou.entity.MailDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.WechatDTO;
import site.yuanzhou.entity.resp.Response;
import site.yuanzhou.service.NoticeSendService;

@Path("/notice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebEntry {

    private static final Logger log = LoggerFactory.getLogger(WebEntry.class);
    private final NoticeSendService noticeSendServiceService;

    public WebEntry(NoticeSendService noticeSendServiceService) {
        this.noticeSendServiceService = noticeSendServiceService;
    }


    /**
     * 发送普通通知
     *
     * @param noticeDTO 通知数据
     * @return void
     */
    @POST
    @Path("/normal")
    public Response<String> sendNotice(NoticeDTO noticeDTO) {
        return Response.success();
    }

    /**
     * 发送邮件通知
     *
     * @param noticeDTO 邮件数据
     * @return void
     */
    @POST
    @Path("/email")
    public Response<String> sendEmailNotice(MailDTO noticeDTO) {
        log.info("发送邮件通知{}", noticeDTO);
        noticeSendServiceService.sendNotice(noticeDTO);
        return Response.success();
    }

    /**
     * 发送微信通知
     *
     * @param noticeDTO 邮件数据
     * @return void
     */
    @POST
    @Path("/wechat")
    public Response<String> sendWechatNotice(WechatDTO noticeDTO) {
        noticeSendServiceService.sendNotice(noticeDTO);
        return Response.success();
    }


    /**
     * 推送gotify通知
     *
     * @param noticeDTO 微信数据
     * @return void
     */
    @POST
    @Path("/gotify")
    public Response<String> sendGotifyNotice(GotifyDTO noticeDTO) {
        noticeSendServiceService.sendNotice(noticeDTO);
        return Response.success();
    }

}
