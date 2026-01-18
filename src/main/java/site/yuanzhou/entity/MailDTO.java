package site.yuanzhou.entity;

import java.util.Objects;

public class MailDTO extends NoticeDTO {

    /**
     * 邮件主题
     */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MailDTO mailDTO = (MailDTO) o;
        return Objects.equals(subject, mailDTO.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subject);
    }
}