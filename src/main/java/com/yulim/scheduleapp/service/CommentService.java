package com.yulim.scheduleapp.service;
import com.yulim.scheduleapp.Comment;
import com.yulim.scheduleapp.dto.CommentCreateRequest;
import com.yulim.scheduleapp.dto.CommentResponse;
import com.yulim.scheduleapp.repository.CommentRepository;
import com.yulim.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private static final int MAX_COMMENT_COUNT = 10;

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponse createComment(Long scheduleId, CommentCreateRequest request) {

        if (!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }

        long commentCount = commentRepository.countByScheduleId(scheduleId);

        if (commentCount >= MAX_COMMENT_COUNT) {
            throw new IllegalArgumentException("댓글은 일정 하나당 최대 10개까지만 작성할 수 있습니다.");
        }

        validateCommentCreateRequest(request);

        Comment comment = new Comment(
                request.getContent(),
                request.getAuthor(),
                request.getPassword(),
                scheduleId
        );

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponse(savedComment);
    }

    private void validateCommentCreateRequest(CommentCreateRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new IllegalArgumentException("댓글 내용은 필수입니다.");
        }

        if (request.getContent().length() > 100) {
            throw new IllegalArgumentException("댓글 내용은 최대 100자까지 가능합니다.");
        }

        if (request.getAuthor() == null || request.getAuthor().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }
}