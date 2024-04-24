package org.xiayudeshu.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.xiayudeshu.pojo.entity.UserData;

import java.time.LocalDateTime;

@Mapper
public interface WriteMapper {
    @Insert("insert into user (neck_name,user_id,avatar,phone,sex,password)" +
            "values"+
            "(#{neckName},#{userId},#{avatar},#{phone},#{sex},#{password})")
    void writeUser(String neckName,Long userId,String avatar,String phone,String sex,String password);

    @Insert("insert into post (post_id,user_id,title,content,pictures,time,tag,subtag)" +
            "values"+
            "(#{postId},#{userId},#{title},#{content},#{pictures},#{time},#{tag},#{subtag})")
    void writePost(Long postId, Long userId, String title, String content, String pictures, LocalDateTime time,String tag,String subtag);

    @Insert("insert into post_comment (post_id,user_id,comment_id,comment,time,parent_id)" +
            "values"+
            "(#{postId},#{userId},#{commentId},#{comment},#{time},#{parentId})")
    void writePostComment(Long postId, Long userId, Long commentId,String comment, LocalDateTime time,Long parentId);



    @Insert("insert into post_favourite (post_id,user_id)" +
            "values"+
            "(#{postId},#{userId})")
    void LikePost(Long postId, Long userId);

    @Insert("insert into lost_favourite (lostCreation_id,user_id)" +
            "values"+
            "(#{lostCreationId},#{userId})")
    void LikeLost(Long lostCreationId, Long userId);

    @Insert("insert into creation_favourite (creation_id,user_id)" +
            "values"+
            "(#{creationId},#{userId})")
    void LikeCreation(Long creationId, Long userId);


    @Insert("insert into creation (creation_id,user_id,title,inform,pictures,time,if_public)" +
            "values"+
            "(#{creationId},#{userId},#{title},#{inform},#{pictures},#{time},#{ifPublic})")
    void writeCreation(Long creationId, Long userId, String title, String inform, String pictures, LocalDateTime time,Boolean ifPublic);


    @Insert("insert into creation_comment (creation_id,user_id,comment_id,comment,time,parent_id)" +
            "values"+
            "(#{creationId},#{userId},#{commentId},#{comment},#{time},#{parentId})")
    void writeCreationComment(Long creationId, Long userId, Long commentId,String comment, LocalDateTime time,Long parentId);


    @Insert("insert into lost_comment (lostcreation_id,user_id,comment_id,comment,time,parent_id)" +
            "values"+
            "(#{lostCreationId},#{userId},#{commentId},#{comment},#{time},#{parentId})")
    void writeLostComment(Long lostCreationId, Long userId, Long commentId,String comment, LocalDateTime time,Long parentId);
}
