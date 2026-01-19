package site.yuanzhou.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import site.yuanzhou.anno.ServiceType;
import site.yuanzhou.config.AppConfig;
import site.yuanzhou.entity.GotifyDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import site.yuanzhou.httpclient.GotifyClient;
import site.yuanzhou.service.NoticeLocalService;

import java.util.Objects;

@ApplicationScoped
@ServiceType(NoticeTypeEnum.GOTIFY)
@Named("gotifyNoticeService")
public class GotifyLocalServiceImpl implements NoticeLocalService {


    private final GotifyClient gotifyClient;

    private final AppConfig config;

    public GotifyLocalServiceImpl(@RestClient GotifyClient gotifyClient, AppConfig config) {
        this.gotifyClient = gotifyClient;
        this.config = config;
    }

    /**
     * 发送通知
     *
     * @param noticeDTO 通知数据
     */
    @Override
    public void sendNotice(NoticeDTO noticeDTO) {
        Objects.requireNonNull(noticeDTO, "通知数据不能为空");
        var token = config.gotify().token();
        if (noticeDTO instanceof GotifyDTO gotifyDTO) {
            gotifyClient.sendNotice(gotifyDTO, token).await().indefinitely();
        }
    }
}
