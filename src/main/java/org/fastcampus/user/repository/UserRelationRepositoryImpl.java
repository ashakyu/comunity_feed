package org.fastcampus.user.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.post_queue.UserPostQueueCommandRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository userRelationRepository;
    private final JpaUserRepository userRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return userRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        userRelationRepository.save(entity);
        userRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());

    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        userRelationRepository.deleteById(id);
        userRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
