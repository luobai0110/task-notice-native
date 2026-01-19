package site.yuanzhou.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "notiflow")
public interface AppConfig {

    Gotify gotify();

    interface Gotify {
        String token();
    }
}
