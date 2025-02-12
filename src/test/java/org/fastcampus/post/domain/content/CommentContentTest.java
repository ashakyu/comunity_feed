package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.comment.CommentContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext(){
        //given
        String contentText = "this is a test content";

        //when
        CommentContent contentContent = new CommentContent(contentText);

        //then
        Assertions.assertEquals(contentText, contentContent.getContentText());
    }

    @Test
    void givenContentLengthIsNotOk_whenCreateCommentContent_thenThrowError(){
        //given
        String contentText = "a".repeat(101);

        //when , given
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));

    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "긁"})
    void givenContentLengthIsNotOk_whenCreateCommentContent_thenThrowError(String contentText){
        //given
        String content = contentText.repeat(101);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsOk_whenCreateCommentContent_thenThrowError(String contentText){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

}
