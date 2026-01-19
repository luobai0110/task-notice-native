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

    public DingNoticeLocalImpl(@RestClient DingTalkRobotClient dingTalkRobotClient, AppConfig appConfig) {
        this.dingTalkRobotClient = dingTalkRobotClient;
        this.config = appConfig;
    }


    @SneakyThrows
    @Override
    public void sendNotice(NoticeDTO noticeDTO) {
        var now = System.currentTimeMillis();
        var sign = SignUtils.getSign(config.dingTalk().secret(), now);
        var dingTalkDTO = new DingTalkDTO();
        dingTalkDTO.setMsgtype("markdown");
        var markdown = new DingTalkDTO.Markdown();
        var content = """
                ### %s
                %s
                """.formatted(noticeDTO.getTitle(), noticeDTO.getMessage());
        markdown.setTitle(noticeDTO.getTitle());
        markdown.setText(content);
        dingTalkDTO.setMarkdown(markdown);
        var resp = dingTalkRobotClient.sendNotice(dingTalkDTO, config.dingTalk().token(), sign, now)
                .await().indefinitely();

        log.info("发送钉钉通知{}", resp);

    }
}
