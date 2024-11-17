package com.travelbuddy.travelplan;

import com.travelbuddy.persistence.domain.dto.travelplan.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/travel-plans")
@RequiredArgsConstructor
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @PostMapping
    public ResponseEntity<Void> createTravelPlan(@RequestBody @Valid TravelPlanCreateRqstDto travelPlanCreateRqstDto) {
        int travelPlanId =  travelPlanService.createTravelPlan(travelPlanCreateRqstDto);

        return ResponseEntity.created(URI.create("/api/travel-plans/" + travelPlanId)).build();
    }

    @PutMapping("/{travelPlanId}")
    public ResponseEntity<Void> updateTravelPlan(@PathVariable int travelPlanId, @RequestBody @Valid TravelPlanUpdateRqstDto travelPlanUpdateRqstDto) {
        travelPlanService.updateTravelPlan(travelPlanId, travelPlanUpdateRqstDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{travelPlanId}/add-member")
    public ResponseEntity<Void> addMemberToTravelPlan(@PathVariable int travelPlanId, @RequestBody @Valid TravelPlanMemberRqstDto travelPlanMemberRqstDto) {
        travelPlanService.addMemberToTravelPlan(travelPlanId, travelPlanMemberRqstDto.getUserId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{travelPlanId}/remove-member")
    public ResponseEntity<Void> removeMemberFromTravelPlan(@PathVariable int travelPlanId, @RequestBody @Valid TravelPlanMemberRqstDto travelPlanMemberRqstDto) {
        travelPlanService.removeMemberFromTravelPlan(travelPlanId, travelPlanMemberRqstDto.getUserId());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{travelPlanId}/change-role")
    public ResponseEntity<Void> changeMemberRole(@PathVariable int travelPlanId, @RequestBody ChgMemberRoleRqstDto chgMemberRoleRqstDto) {
        travelPlanService.changeMemberRole(travelPlanId, chgMemberRoleRqstDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{travelPlanId}/add-site")
    public ResponseEntity<Void> addSiteToTravelPlan(@PathVariable int travelPlanId, @RequestBody TravelPlanSiteCreateRqstDto travelPlanSiteCreateRqstDto) {
        travelPlanService.addSiteToTravelPlan(travelPlanId, travelPlanSiteCreateRqstDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{travelPlanId}/remove-site")
    public ResponseEntity<Void> removeSiteFromTravelPlan(@PathVariable int travelPlanId, @RequestBody TravelPlanSiteDeleteRqstDto travelPlanSiteDeleteRqstDto) {
        travelPlanService.removeSiteFromTravelPlan(travelPlanId, travelPlanSiteDeleteRqstDto.getSiteId());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{travelPlanId}/update-site")
    public ResponseEntity<Void> updateSiteInTravelPlan(@PathVariable int travelPlanId, @RequestBody TravelPlanSiteUpdateRqstDto travelPlanSiteUpdateRqstDto) {
        travelPlanService.updateSiteInTravelPlan(travelPlanId, travelPlanSiteUpdateRqstDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{travelPlanId}")
    public ResponseEntity<Void> deleteTravelPlan(@PathVariable int travelPlanId) {
        travelPlanService.deleteTravelPlan(travelPlanId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{travelPlanId}")
    public ResponseEntity<Object> getTravelPlan(@PathVariable int travelPlanId) {
        TravelPlanRspnDto travelPlan = travelPlanService.getTravelPlan(travelPlanId);

        return ResponseEntity.ok(travelPlan);
    }

    @GetMapping
    public ResponseEntity<Object> getTravelPlans() {
        List<TravelPlanBasicRspnDto> travelPlans = travelPlanService.getTravelPlans();

        return ResponseEntity.ok(travelPlans);
    }
}
