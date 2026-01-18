package site.yuanzhou.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import site.yuanzhou.anno.ServiceType;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import site.yuanzhou.service.NoticeLocalService;


@ServiceType(value = NoticeTypeEnum.WECHAT)
@ApplicationScoped
@Named("wechatNoticeService")
public class WechatNoticeServiceImpl implements NoticeLocalService {
    @Override
    public void sendNotice(NoticeDTO noticeDTO) {

    }
}
