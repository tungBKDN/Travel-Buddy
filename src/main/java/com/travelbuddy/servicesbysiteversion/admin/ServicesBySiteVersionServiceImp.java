package com.travelbuddy.servicesbysiteversion.admin;

import com.travelbuddy.persistence.domain.entity.ServicesBySiteVersionEntity;
import com.travelbuddy.persistence.repository.ServicesBySiteVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesBySiteVersionServiceImp implements ServicesBySiteVersionService {
    private final ServicesBySiteVersionRepository servicesBySiteVersionRepository;
    @Override
    public void createServicesBySiteVersion(int siteVersionId, int serviceId) {
        ServicesBySiteVersionEntity servicesBySiteVersion = ServicesBySiteVersionEntity.builder().siteVersionId(siteVersionId).serviceId(serviceId).build();
        servicesBySiteVersionRepository.save(servicesBySiteVersion);
    }

    @Override
    public void createServicesBySiteVersion(int siteVersionId, List<Integer> serviceIds) {
        ArrayList<ServicesBySiteVersionEntity> servicesBySiteVersionList = new ArrayList<>();
        for (int serviceId : serviceIds) {
            servicesBySiteVersionList.add(ServicesBySiteVersionEntity.builder().siteVersionId(siteVersionId).serviceId(serviceId).build());
        }
        servicesBySiteVersionRepository.saveAll(servicesBySiteVersionList);
    }

    @Override
    public void removeServicesBySiteVersion(int siteVersionId, int serviceId) {
        servicesBySiteVersionRepository.deleteBySiteVersionIdAndServiceId(siteVersionId, serviceId);
    }

    @Override
    public void removeServicesBySiteVersion(int siteVersionId) {
        servicesBySiteVersionRepository.deleteAllBySiteVersionId(siteVersionId);
    }
}
