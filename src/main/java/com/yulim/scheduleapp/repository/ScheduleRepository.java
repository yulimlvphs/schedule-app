package com.yulim.scheduleapp.repository;
import com.yulim.scheduleapp.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}