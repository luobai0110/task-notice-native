package site.yuanzhou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MailDTO extends NoticeDTO {

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件类型
     */
    private Integer mailType;
}