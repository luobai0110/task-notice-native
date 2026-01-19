package site.yuanzhou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DingTalkDTO extends NoticeDTO {

    private String msgtype;

    private Text text;
    private At at;
    private Link link;
    private Markdown markdown;
    private ActionCard actionCard;
    private FeedCard feedCard;

    // ============ 消息体定义 ============

    @Data
    public static class Text {
        private String content;

        public Text(String content) {
            this.content = content;
        }

        public Text() {
        }
    }

    @Data
    public static class At {
        private boolean isAtAll;
        private List<String> atMobiles;
        private List<String> atUserIds;
    }

    @Data
    public static class Link {
        private String text;
        private String title;
        private String picUrl;
        private String messageUrl;
    }

    @Data
    public static class Markdown {
        private String title;
        private String text; // 支持 Markdown 语法
    }

    @Data
    public static class ActionCard {
        private String title;
        private String text; // Markdown 格式
        private String btnOrientation; // "0"=按钮竖直排列, "1"=横向
        private String hideAvatar;     // "0"=正常发消息者头像, "1"=隐藏

        // 以下两种互斥：singleTitle/singleURL 用于整体跳转；btns 用于独立按钮
        private String singleTitle;
        private String singleURL;

        private List<Btn> btns;
    }

    @Data
    public static class Btn {
        private String title; // 按钮标题
        private String actionURL; // 点击后跳转 URL
    }

    @Data
    public static class FeedCard {
        private List<FeedLink> links;
    }

    @Data
    public static class FeedLink {
        private String title;
        private String messageUrl;
        private String picUrl;
    }
}
