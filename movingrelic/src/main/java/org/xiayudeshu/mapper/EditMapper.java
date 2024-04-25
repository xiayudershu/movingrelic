package org.xiayudeshu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.xiayudeshu.pojo.entity.UserData;

@Mapper
public interface EditMapper {
    @Update("update user set neck_name=#{neckName},avatar=#{avatar},phone=#{phone},sex=#{sex},password=#{password} where user_id=#{userId}")
    void updateUserInform(UserData userData);

    @Update("update post set favouriteNum=#{favouriteNum} where post_id=#{postId}")
    void updatePostFavouriteNum(Long postId, Long favouriteNum);

    @Update("update creation set favouriteNum=#{favouriteNum} where creation_id=#{creationId}")
    void updateCreationFavouriteNum(Long creationId, Long favouriteNum);

    @Update("update lost set favouriteNum=#{favouriteNum} where lostCreation_id=#{lostCreationId}")
    void updateLostFavouriteNum(Long lostCreationId, Long favouriteNum);
}
