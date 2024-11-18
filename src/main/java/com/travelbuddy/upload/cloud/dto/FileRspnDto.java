package com.travelbuddy.upload.cloud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FileRspnDto {
    private String id;
    private String url;
//    private String createdAt;
}
