package com.yulim.scheduleapp.service;
import com.yulim.scheduleapp.Schedule;
import com.yulim.scheduleapp.dto.ScheduleCreateRequest;
import com.yulim.scheduleapp.dto.ScheduleResponse;
import com.yulim.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponse(savedSchedule);
    }

    public List<ScheduleResponse> findSchedules(String author) {
        List<Schedule> schedules;

        if(author == null || author.isBlank()) {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else {
            schedules = scheduleRepository.findByAuthorOrderByModifiedAtDesc(author);
        }

        return schedules.stream()
                .map(ScheduleResponse::new)
                .toList();
    }

    public ScheduleResponse findSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));

        return new ScheduleResponse(schedule);
    }
}