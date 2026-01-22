package site.yuanzhou.service.impl;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import site.yuanzhou.anno.ServiceType;
import site.yuanzhou.entity.MailDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.EmailType;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import site.yuanzhou.service.NoticeLocalService;

import java.util.Objects;

@Slf4j
@ServiceType(NoticeTypeEnum.E_MAIL)
@ApplicationScoped
@Named("emailNoticeService")
public class EMailNoticeServiceImpl implements NoticeLocalService {

    private final ReactiveMailer reactiveMailer;


    public EMailNoticeServiceImpl(ReactiveMailer reactiveMailer) {
        this.reactiveMailer = reactiveMailer;
    }

    @Override
    public void sendNotice(NoticeDTO noticeDTO) {
        if (noticeDTO instanceof MailDTO mailDTO) {
            Mail mail;
            if (Objects.equals(EmailType.HTML.getCode(), mailDTO.getMailType())) {
                mail = Mail.withHtml(mailDTO.getTo(), mailDTO.getSubject(), mailDTO.getMessage());
            } else {
                mail = Mail.withText(mailDTO.getTo(), mailDTO.getSubject(), mailDTO.getMessage());
            }
            mail.setFrom(mailDTO.getFrom());
            reactiveMailer.send(mail).await().indefinitely();
            log.info("EMailNoticeServiceImpl#sendNotice: 发送邮件通知{}", mailDTO);
        }
    }
}
