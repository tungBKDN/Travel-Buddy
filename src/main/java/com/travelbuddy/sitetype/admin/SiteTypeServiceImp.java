package com.travelbuddy.sitetype.admin;

import com.travelbuddy.common.exception.errorresponse.EnumNotFitException;
import com.travelbuddy.common.mapper.PageMapper;
import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.mapper.SiteTypeMapper;
import com.travelbuddy.persistence.repository.SiteTypeRepository;
import com.travelbuddy.common.constants.DualStateEnum;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeCreateRqstDto;
import com.travelbuddy.persistence.domain.dto.sitetype.SiteTypeRspnDto;
import com.travelbuddy.persistence.domain.entity.SiteTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import static com.travelbuddy.common.constants.PaginationLimitConstants.SITE_TYPE_LIMIT;

@Service
@RequiredArgsConstructor
public class SiteTypeServiceImp implements SiteTypeService {
    private final SiteTypeRepository siteTypeRepository;
    private final SiteTypeMapper siteTypeMapper;
    private final PageMapper pageMapper;

    @Override
    public Integer createSiteType(SiteTypeCreateRqstDto siteTypeCreateRqstDto) {
        DualStateEnum dualState;
        try {
            dualState = DualStateEnum.valueOf(siteTypeCreateRqstDto.getMode());
        } catch (IllegalArgumentException e) {
            throw new EnumNotFitException("Invalid mode: " + siteTypeCreateRqstDto.getMode());
        }

        SiteTypeEntity siteType = SiteTypeEntity.builder()
                .typeName(siteTypeCreateRqstDto.getSiteTypeName())
                .dualState(DualStateEnum.valueOf(siteTypeCreateRqstDto.getMode()))
                .build();

        return siteTypeRepository.save(siteType).getId();
    }

    @Override
    public PageDto<SiteTypeRspnDto> getAllSiteTypes(int page) {
        Pageable pageable = PageRequest.of(page - 1, SITE_TYPE_LIMIT, Sort.by("typeName"));
        Page<SiteTypeEntity> siteTypes = siteTypeRepository.findAll(pageable);
        return pageMapper.toPageDto(siteTypes.map(siteTypeMapper::siteTypeEntityToSiteTypeRspnDto));
    }

    @Override
    public PageDto<SiteTypeRspnDto> searchSiteTypes(String siteTypeSearch, int page) {
        Pageable pageable = PageRequest.of(page - 1, SITE_TYPE_LIMIT, Sort.by("typeName"));
        Page<SiteTypeEntity> siteTypes = siteTypeRepository.searchSiteTypeEntitiesByTypeNameContainingIgnoreCase(siteTypeSearch, pageable);
        return pageMapper.toPageDto(siteTypes.map(siteTypeMapper::siteTypeEntityToSiteTypeRspnDto));
    }
}
