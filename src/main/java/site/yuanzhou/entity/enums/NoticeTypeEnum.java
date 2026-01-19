package site.yuanzhou.entity.enums;

public enum NoticeTypeEnum {

    E_MAIL(1, "email"),
    WECHAT(2, "wechat"),
    GOTIFY(3, "gotify"),
    DING_TALK(4, "dingTalk"),
    OTHER(99, "other");

    final Integer code;
    final String value;

    NoticeTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static NoticeTypeEnum getByCode(Integer code) {
        for (NoticeTypeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return OTHER;
    }


    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
