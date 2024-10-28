package com.travelbuddy.openingTime.user;

import com.travelbuddy.common.constants.DayOfWeekEnum;
import com.travelbuddy.common.exception.errorresponse.BadTimingInputException;
import com.travelbuddy.persistence.domain.dto.site.OpeningTimeCreateRqstDto;
import com.travelbuddy.persistence.domain.entity.OpeningTimeEntity;
import com.travelbuddy.persistence.repository.OpeningTimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpeningTimeServiceImp implements OpeningTimeService {
    private final OpeningTimeRepository OpeningTimeRepository;

    public OpeningTimeServiceImp(OpeningTimeRepository OpeningTimeRepository) {
        this.OpeningTimeRepository = OpeningTimeRepository;
    }

    public void addOpeningTimes(List<OpeningTimeCreateRqstDto> openingTimeCreateRqstDtoList, Integer siteVersionId) {
        openingTimeCreateRqstDtoList.forEach(openingTimeCreateRqstDto -> {
            if (openingTimeCreateRqstDto.isCloseTimeBeforeOpenTime()) {
                throw new BadTimingInputException("BAD-INPUT", "Close time should be after open time");
            }
        });

        // Sort the list by openingTimeCreateRqstDto.evaluateStartTime()
        openingTimeCreateRqstDtoList.sort((o1, o2) -> o1.evaluateOpenTime().compareTo(o2.evaluateOpenTime()));

        // Check overlapping time
        for (int i = 0; i < openingTimeCreateRqstDtoList.size() - 1; i++) {
            if (openingTimeCreateRqstDtoList.get(i).evaluateCloseTime().compareTo(openingTimeCreateRqstDtoList.get(i + 1).evaluateOpenTime()) > 0) {
                throw new BadTimingInputException("SESSION-OVERLAPPED", "Overlapping time");
            }
        }

        ArrayList<OpeningTimeEntity> openingTimeEntities = new ArrayList<>();
        for (OpeningTimeCreateRqstDto openingTimeCreateRqstDto : openingTimeCreateRqstDtoList) {
            openingTimeEntities.add(OpeningTimeEntity.builder().siteVersionId(siteVersionId)
                    .dayOfWeek(DayOfWeekEnum.valueOf(openingTimeCreateRqstDto.getDayOfWeek()))
                    .openTime(openingTimeCreateRqstDto.getOpenTime())
                    .closeTime(openingTimeCreateRqstDto.getCloseTime())
                    .build());
        }

        // Save the opening time
        OpeningTimeRepository.saveAll(openingTimeEntities);
    }
}
