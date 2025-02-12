package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentReqeustDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCratedComment_thenReturnComment(){
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        String content = comment.getContent();
        Assertions.assertEquals(commentContentText, content);

    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        UpdateCommentReqeustDto updateCommentReqeustDto = new UpdateCommentReqeustDto(comment.getId(), user.getId(), "updated-comment");
        Comment updatedComment = commentService.updateComment(updateCommentReqeustDto);

        //then
        Assertions.assertEquals(updatedComment.getId(), comment.getId());
        Assertions.assertEquals(updatedComment.getAuthor(), comment.getAuthor());
        Assertions.assertEquals(updatedComment.getContent(), comment.getContent());

    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        Assertions.assertEquals(comment.getLikeCount(), 1);
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithUnlike(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);
        //then
        Assertions.assertEquals(comment.getLikeCount(), 0);
    }

}
