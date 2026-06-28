package com.yulim.scheduleapp.repository;
import com.yulim.scheduleapp.Schedule;
import org.h2.schema.SchemaObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();

    List<Schedule> findByAuthorOrderByModifiedAtDesc(String author);
}