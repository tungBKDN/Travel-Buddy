package com.travelbuddy.servicesbysiteversion.admin;

import java.util.List;

public interface ServicesBySiteVersionService {
    void createServicesBySiteVersion(int siteVersionId, int serviceId);
    void createServicesBySiteVersion(int siteVersionId, List<Integer> serviceIds);
    void removeServicesBySiteVersion(int siteVersionId, int serviceId);
    void removeServicesBySiteVersion(int siteVersionId);
}
