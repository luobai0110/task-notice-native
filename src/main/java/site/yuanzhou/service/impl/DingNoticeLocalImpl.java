package site.yuanzhou.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import site.yuanzhou.anno.ServiceType;
import site.yuanzhou.config.AppConfig;
import site.yuanzhou.entity.DingTalkDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import site.yuanzhou.httpclient.DingTalkRobotClient;
import site.yuanzhou.service.NoticeLocalService;
import site.yuanzhou.util.SignUtils;


@Slf4j
@ApplicationScoped
@ServiceType(NoticeTypeEnum.DING_TALK)
public class DingNoticeLocalImpl implements NoticeLocalService {

    private final DingTalkRobotClient dingTalkRobotClient;

    private final AppConfig config;
    private final ObjectMapper objectMapper;

    public DingNoticeLocalImpl(@RestClient DingTalkRobotClient dingTalkRobotClient, AppConfig appConfig, ObjectMapper objectMapper) {
        this.dingTalkRobotClient = dingTalkRobotClient;
        this.config = appConfig;
        this.objectMapper = objectMapper;
    }


    @SneakyThrows
    @Override
    public void sendNotice(NoticeDTO noticeDTO) {
        var now = System.currentTimeMillis();
        var sign = SignUtils.getSign(config.dingTalk().secret(), now);
        var dingTalkDTO = new DingTalkDTO();
        dingTalkDTO.setMsgtype("text");
        dingTalkDTO.setText(new DingTalkDTO.Text(noticeDTO.getMessage()));
//        var json = objectMapper.writeValueAsString(dingTalkDTO);
//        log.info("发送钉钉通知{}", json);
        var resp = dingTalkRobotClient.sendNotice(dingTalkDTO, config.dingTalk().token(), sign, now)
                .await().indefinitely();

        log.info("发送钉钉通知{}", resp);

    }
}
