package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommentTest {
    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user , new PostContent("content"));

    private final Comment comment = new Comment(1L,post,user,new CommentContent("content"));
    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1(){
        //when
        comment.like(otherUser);

        //then
        Assertions.assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowException(){
        ///when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnLike_thenLikeCountShouldBe0(){
        //given
        comment.like(otherUser);

        //when
        comment.unlike();

        //then
        Assertions.assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnLike_thenLikeCountShouldBe0(){
        //when
        comment.unlike();

        //then
        Assertions.assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenShouldBeUpdated(){
        //given
        String newCommentContent = "new Content";

        //when
        comment.updateComment(user, newCommentContent);

        Assertions.assertEquals(newCommentContent, comment.getContent());

    }

    @Test
    void givenCOmment_whenUpdateContentOver100_thenthrowException(){
        //given
        String newCommentContent = "a".repeat(101);

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }


}
