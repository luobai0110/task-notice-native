package site.yuanzhou.entity.enums;


import lombok.Getter;

@Getter
public enum EmailType {
    HTML(1, "html"), TEXT(2, "text");

    final Integer code;
    final String name;

    EmailType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
