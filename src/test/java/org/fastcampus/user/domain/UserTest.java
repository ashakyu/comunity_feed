package org.fastcampus.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init(){
        //매번 새로운 객체 생성
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse(){
        //when
        boolean isSame = user1.equals(user2);

        //then
        Assertions.assertFalse(isSame);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue(){
        //given
        User sameUser = new User(1L, userInfo);
        //when
        boolean isSame = user1.equals(sameUser);

        //then
        Assertions.assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenNotEqual(){
        //when
        int haseCode1 = user1.hashCode();
        int haseCode2 = user2.hashCode();

        //then
        assertNotEquals(haseCode1, haseCode2);
    }


    @Test
    void givenTwoSameIdUser_whenHashCode_thenEqual(){
        //when
        User sameUser = new User(1L, userInfo);
        //when

        int haseCode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        //then
        assertEquals(haseCode1, sameUserHashCode);
    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUser1FollowUser2_thenIncreaseUserCount(){
        //when
        user1.follow(user2);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUnfollow_thenDecreaseUserCount(){
        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount(){
        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
}
