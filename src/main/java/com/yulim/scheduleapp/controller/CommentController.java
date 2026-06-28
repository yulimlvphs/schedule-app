package com.yulim.scheduleapp.controller;
import com.yulim.scheduleapp.dto.CommentCreateRequest;
import com.yulim.scheduleapp.dto.CommentResponse;
import com.yulim.scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedules/{schedules}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(@PathVariable Long scheduleId, @RequestBody CommentCreateRequest request) {
        return commentService.createComment(scheduleId, request);
    }
}
