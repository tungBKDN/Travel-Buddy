package com.travelbuddy.persistence.domain.dto.sitereview;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MediaRspnDto {
    private Integer id;
    private String url;
    private String mediaType;
    private String createdAt;
}
