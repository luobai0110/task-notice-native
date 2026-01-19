package site.yuanzhou.service;

import site.yuanzhou.entity.NoticeDTO;

public interface NoticeLocalService {

    /**
     * 发送通知
     * @param noticeDTO 通知数据
     */
    void sendNotice(NoticeDTO noticeDTO);
}
