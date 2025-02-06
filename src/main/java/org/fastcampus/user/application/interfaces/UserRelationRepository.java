package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {
    //UserId를 넘기지 않고 User 객체를 넘기는 이유?
    /*
    * 유저의 팔로우, 팔로잉 카운트도 같이 업데이트 되어야 하기때문에..
    * 트랜잭션으로 단위로 변경이 발생되는 것들은 같이 메소드 하나로 묶어주면 유지보수가 쉬어진다.
    * 유저 전체를 파라미터로 해서 도메인 전체를 파라미터로 던진다면
    * 유저 내부만 변경을 해도 인터페이스에는 변화가 없다.
    * */

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);


}
