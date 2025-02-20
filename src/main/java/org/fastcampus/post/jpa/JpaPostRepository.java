package org.fastcampus.post.jpa;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p.id FROM PostEntity p WHERE p.author.id = :author_id ")
    List<Long> findAllPostIdsByAuthorId(Long author_id);


    @Modifying
    @Query(value = "UPDATE PostEntity p "
                    + "SET p.content = :#{#postEntity.content}, "
                    + "p.state = :#{#postEntity.getState}, "
                    + "p.updDt = now() "
                    + "WHERE p.id = :#{#postEntity.id}" )
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value="UPDATE PostEntity p "
                + "SET p.likeCount = :#{#postEntity.likeCount}, "
                + "p.updDt = now() "
                + "WHERE p.id = :#{#postEntity.getId}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value="UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1, "
            + "p.updDt = now() "
            + "WHERE p.id = :id")
    void increaseCommentLikeCount(Long id);



}
