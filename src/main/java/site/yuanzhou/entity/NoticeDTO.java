package site.yuanzhou.entity;

import site.yuanzhou.entity.enums.NoticeTypeEnum;

import java.io.Serializable;
import java.util.Objects;

public class NoticeDTO  implements Serializable {

    /**
     * 通知内容
     */
    private String message;
    /**
     * 来源
     */
    private String from;
    /**
     * 目标
     */
    private String to;
    /**
     * 类型
     *
     * @see NoticeTypeEnum
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "content='" + message + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type=" + type +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NoticeDTO noticeDTO = (NoticeDTO) o;
        return Objects.equals(message, noticeDTO.message) && Objects.equals(from, noticeDTO.from) && Objects.equals(to, noticeDTO.to) && Objects.equals(type, noticeDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, from, to, type);
    }
}

