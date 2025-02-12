package org.fastcampus.user.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService  = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
       this.user1 = userService.createUser(dto);
       this.user2 = userService.createUser(dto);

       this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }


    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollwSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        Assertions.assertEquals(1, user1.followingCount());
        Assertions.assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoFollowed_whenFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);

        //when , then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUserFollow_whenUnfollow_thenUserFollweSaved() {
        //given
        userRelationService.follow(requestDto);

        //when

        userRelationService.unfollow(requestDto);

        //then
        Assertions.assertEquals(0, user1.followingCount());
        Assertions.assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError() {
        //when , then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }

}
