package com.yulim.scheduleapp.repository;
import com.yulim.scheduleapp.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByScheduleId(Long scheduleId);
}
