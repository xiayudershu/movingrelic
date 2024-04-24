package org.xiayudeshu.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeletMapper {
    @Delete("delete from post_favourite where post_id=#{postId} and user_id=#{userId}")
    void DislikePost(Long postId,Long userId);

    @Delete("delete from creation_favourite where creation_id=#{creationId} and user_id=#{userId}")
    void DislikeCreation(Long creationId,Long userId);

    @Delete("delete from lost_favourite where lostCreation_id=#{lostCreationId} and user_id=#{userId}")
    void DislikeLost(Long lostCreationId,Long userId);
}
