package site.yuanzhou.service;


import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.yuanzhou.entity.NoticeDTO;

@ApplicationScoped
public class NoticeSendService {

    private final NoticeFactory noticeFactory;

    public NoticeSendService(NoticeFactory noticeFactory) {
        this.noticeFactory = noticeFactory;
    }

    public void sendNotice(NoticeDTO noticeDTO) {
        var service = noticeFactory.getNoticeWay(noticeDTO.getType());
        service.sendNotice(noticeDTO);
    }
}
