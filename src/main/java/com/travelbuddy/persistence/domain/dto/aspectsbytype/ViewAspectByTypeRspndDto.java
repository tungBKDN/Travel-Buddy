package com.travelbuddy.persistence.domain.dto.aspectsbytype;

import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewAspectByTypeRspndDto {
    private Integer aspectId;
    private SiteTypeEntity siteType;
    private String aspectName;
}