package site.yuanzhou.entry;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import site.yuanzhou.entity.MailDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.resp.Response;
import site.yuanzhou.service.NoticeSendService;

@Path("/notice")
public class WebEntry {

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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email")
    public Response<String> sendEmailNotice(MailDTO noticeDTO) {
        noticeSendServiceService.sendNotice(noticeDTO);
        return Response.success();
    }
}
