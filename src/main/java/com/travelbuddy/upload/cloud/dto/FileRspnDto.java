package com.travelbuddy.upload.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRspnDto {
    private String id;
    private String url;
//    private String createdAt;
}
