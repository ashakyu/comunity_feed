package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate{
    @Test
    void givenPostRequestDto_whenCreated_thenReturnPost(){
        //when
        Post savedPost = postService.createPost(postRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        Assertions.assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost(){
        //given
        Post savedPost = postService.createPost(postRequestDto);

        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(), user.getId(), "this is test updated content", PostPublicationState.PUBLIC);

        //when
        Post updatedPost = postService.updatePost(savedPost.getId(), updateDto);

        //then
        Assertions.assertEquals(savedPost.getId(), updatedPost.getId());
        Assertions.assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        Assertions.assertEquals(savedPost.getContent(), updatedPost.getContent());

    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike(){
        //given
        Post savedPost = postService.createPost(postRequestDto);

        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);

        //when
        Assertions.assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnliked_thenReturnPostWithoutLike(){
        //given
        Post savedPost = postService.createPost(postRequestDto);

        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        postService.unlikePost(likeDto);
        //when
        Assertions.assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenWithoutLikeAndUnliked_thenReturnPostWithoutLike(){
        //given
        Post savedPost = postService.createPost(postRequestDto);

        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeDto);
        //when
        Assertions.assertEquals(0, savedPost.getLikeCount());
    }
}
