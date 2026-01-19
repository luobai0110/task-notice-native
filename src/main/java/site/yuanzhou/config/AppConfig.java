package site.yuanzhou.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "notiflow")
public interface AppConfig {

    Gotify gotify();

    DingTalk dingTalk();

    interface Gotify {
        String token();
    }

    interface DingTalk {
        String token();
        String secret();
    }
}
