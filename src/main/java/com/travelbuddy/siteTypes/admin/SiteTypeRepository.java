package com.travelbuddy.siteTypes.admin;

import com.travelbuddy.siteTypes.user.SiteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SiteTypeRepository extends JpaRepository<SiteTypeEntity, Integer> {
    // Method to select a site type by its name
    @Query("SELECT s FROM SiteTypeEntity s WHERE UPPER(s.siteType) = UPPER(:siteType)")
    Optional<SiteTypeEntity> findBySiteType(String siteType);

    // Checking if a site type exists -> true/false
    default boolean existsBySiteType(String siteType) {
        return findBySiteType(siteType).isPresent();
    }

    // Return the ID of the site type with the given name
    @Query("SELECT s.ID FROM SiteTypeEntity s WHERE UPPER(s.siteType) = UPPER(:siteType)")
    Optional<Integer> findIdBySiteType(String siteType);

    // Select all site types
    @Query("SELECT s FROM SiteTypeEntity s")
    Optional<List<SiteTypeEntity>> getAllSiteTypes();

    // Select all site types with pagination
    @Query(value = "SELECT s FROM site_types s LIMIT :limit OFFSET :paging", nativeQuery = true)
    Optional<List<SiteTypeEntity>> getAllSiteTypes(Integer paging, Integer limit);
}
