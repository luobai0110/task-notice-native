package site.yuanzhou.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import site.yuanzhou.anno.ServiceType;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import site.yuanzhou.service.NoticeLocalService;


@ApplicationScoped
@ServiceType(NoticeTypeEnum.OTHER)
@Named("normalNoticeService")
public class NoticeLocalServiceImpl implements NoticeLocalService {
    @Override
    public void sendNotice(NoticeDTO noticeDTO) {

    }
}
