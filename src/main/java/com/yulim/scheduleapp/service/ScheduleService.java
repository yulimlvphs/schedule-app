package com.yulim.scheduleapp.service;
import com.yulim.scheduleapp.Schedule;
import com.yulim.scheduleapp.dto.ScheduleCreateRequest;
import com.yulim.scheduleapp.dto.ScheduleResponse;
import com.yulim.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}