package com.yulim.scheduleapp.controller;
import com.yulim.scheduleapp.dto.*;
import com.yulim.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse createSchedule(
            @RequestBody ScheduleCreateRequest request
    ) {
        return scheduleService.createSchedule(request);
    }

    @GetMapping
    public List<ScheduleResponse> findSchedules(
            @RequestParam(required = false) String author
    ) {
        return scheduleService.findSchedules(author);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleDetailResponse findSchedule(
            @PathVariable Long scheduleId
    ) {
        return scheduleService.findSchedule(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return scheduleService.updateSchedule(scheduleId,request);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDeleteRequest request
    ) {
        scheduleService.deleteSchedule(scheduleId, request);
    }
}
