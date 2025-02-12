package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user , new PostContent("content"));

    @Test
    void givePostCreateW_whenLike_thenLikeCountShouldBe1(){
        //when
        post.like(otherUser);

        //then
        Assertions.assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowException(){
        //when , given
        Assertions.assertThrows(RuntimeException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreateAndLike_whenUnlike_thenLikeCountShouldBe0(){
        //given
        post.like(otherUser);

        //when
        post.unlike();

        //then
        Assertions.assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0(){
        //when
        post.unlike();

        //then
        Assertions.assertEquals(0, post.getLikeCount());

    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContenShouldBeUpdated(){
        //given
        String newPostContent = "new content";

        //when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        Assertions.assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowException(){
        //given
        String newPostContent = "new content";

        Assertions.assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }

}
