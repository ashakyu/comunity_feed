package org.fastcampus.post.application.dto;

public record UpdateCommentReqeustDto(Long commentId, Long userId, String content) {
}
