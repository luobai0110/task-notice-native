package site.yuanzhou.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import site.yuanzhou.anno.ServiceType;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class NoticeFactory {

    private final Map<Integer, NoticeLocalService> noticeServices;

    public NoticeFactory(@Any Instance<NoticeLocalService> noticeServices) {
        this.noticeServices = noticeServices.
                stream().collect(Collectors.toMap(
                        k -> {
                            var cls = k.getClass();
                            var anno = cls.getAnnotation(ServiceType.class);
                            if (anno == null) {
                                var superCls = cls.getSuperclass();
                                if (superCls != null) {
                                    anno = superCls.getAnnotation(ServiceType.class);
                                }
                            }
                            return anno.value().getCode();
                        },
                        Function.identity())
                );
    }


    public NoticeLocalService getNoticeWay(Integer way) {
        return noticeServices.get(way);
    }
}
