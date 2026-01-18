package site.yuanzhou.service.impl;

import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import site.yuanzhou.entity.MailDTO;
import site.yuanzhou.entity.NoticeDTO;
import site.yuanzhou.entity.enums.NoticeTypeEnum;
import site.yuanzhou.proto.MailGrpcDTO;
import site.yuanzhou.proto.NoticeGrpcDTO;
import site.yuanzhou.proto.NoticeService;
import site.yuanzhou.proto.WechatGrpcDTO;
import site.yuanzhou.service.NoticeSendService;

@GrpcService
public class NoticeGrpcServiceImpl implements NoticeService {

    private final NoticeSendService noticeSendService;

    public NoticeGrpcServiceImpl(NoticeSendService noticeSendService) {
        this.noticeSendService = noticeSendService;
    }

    @Override
    public Uni<Empty> sendMailNotice(MailGrpcDTO request) {
        MailDTO dto = new MailDTO();
        dto.setContent(request.getContent());
        if (request.hasFrom()) dto.setFrom(request.getFrom());
        if (request.hasTo()) dto.setTo(request.getTo());
        if (request.hasType()) {
            dto.setType(request.getType());
        } else {
            dto.setType(NoticeTypeEnum.E_MAIL.getCode());
        }
        dto.setSubject(request.getSubject());
        noticeSendService.sendNotice(dto);
        return Uni.createFrom().item(Empty.getDefaultInstance());
    }

    @Override
    public Uni<Empty> sendNotice(NoticeGrpcDTO request) {
        NoticeDTO dto = new NoticeDTO();
        dto.setContent(request.getContent());
        dto.setFrom(request.getFrom());
        dto.setTo(request.getTo());
        dto.setType((int) request.getType());
        noticeSendService.sendNotice(dto);
        return Uni.createFrom().item(Empty.getDefaultInstance());
    }

    @Override
    public Uni<Empty> sendWechatNotice(WechatGrpcDTO request) {
        NoticeDTO dto = new NoticeDTO();
        dto.setContent(request.getContent());
        dto.setFrom(request.getFrom());
        dto.setTo(request.getTo());
        dto.setType((int) request.getType());
        if (dto.getType() == null) {
            dto.setType(NoticeTypeEnum.WECHAT.getCode());
        }
        noticeSendService.sendNotice(dto);
        return Uni.createFrom().item(Empty.getDefaultInstance());
    }
}
