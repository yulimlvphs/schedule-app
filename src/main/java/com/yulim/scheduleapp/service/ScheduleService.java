package com.yulim.scheduleapp.service;
import com.yulim.scheduleapp.Schedule;
import com.yulim.scheduleapp.dto.*;
import com.yulim.scheduleapp.repository.CommentRepository;
import com.yulim.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        validateScheduleCreateRequest(request);

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

    public ScheduleDetailResponse findSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));

        List<CommentResponse> comments = commentRepository.findByScheduleId(scheduleId)
                .stream()
                .map(CommentResponse::new)
                .toList();

        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        validateScheduleUpdateRequest(request);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다. "));

        if(schedule.isPasswordMismatch(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(request.getTitle(), request.getAuthor());

        return new ScheduleResponse(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, ScheduleDeleteRequest request) {
        validateScheduleDeleteRequest(request);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 일정입니다."));

        if(schedule.isPasswordMismatch(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }

    private void validateScheduleCreateRequest(ScheduleCreateRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("일정 제목은 필수입니다.");
        }

        if (request.getTitle().length() > 30) {
            throw new IllegalArgumentException("일정 제목은 최대 30자까지 가능합니다.");
        }

        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new IllegalArgumentException("일정 내용은 필수입니다.");
        }

        if (request.getContent().length() > 200) {
            throw new IllegalArgumentException("일정 내용은 최대 200자까지 가능합니다.");
        }

        if (request.getAuthor() == null || request.getAuthor().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    private void validateScheduleUpdateRequest(ScheduleUpdateRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("일정 제목은 필수입니다.");
        }

        if (request.getTitle().length() > 30) {
            throw new IllegalArgumentException("일정 제목은 최대 30자까지 가능합니다.");
        }

        if (request.getAuthor() == null || request.getAuthor().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    private void validateScheduleDeleteRequest(ScheduleDeleteRequest request) {
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }
}