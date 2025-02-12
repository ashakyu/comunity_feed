package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        //given
        String text = "this is fine";

        //when
        PostContent postContent = new PostContent(text);

        //then
        Assertions.assertEquals(text, postContent.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError(){
        //given
        String content = "a".repeat(501);

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }


    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슲"}) // 파라미터로 넣고 싶은 값들을 세팅
    void givenContentLengthIsOverANdKorean_whenCreated_thenThrowError(String koreanWord){
        //given
        String content = koreanWord.repeat(501);

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(content));

    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError(){
        //given
        String content = "a".repeat(4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(content));

    }

    @ParameterizedTest
    @NullAndEmptySource // 파라미터에 null 값
    void givenContentIsEmpty_whenCreated_thenThrowError(String content){
        //when , then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }
}
