package org.xiayudeshu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.xiayudeshu.pojo.entity.UserData;

@Mapper
public interface EditMapper {
    @Update("update user set neck_name=#{neckName},avatar=#{avatar},phone=#{phone},sex=#{sex},password=#{password} where user_id=#{userId}")
    void updateUserInform(UserData userData);
}
