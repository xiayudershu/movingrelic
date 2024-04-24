package org.xiayudeshu.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xiayudeshu.pojo.entity.*;

import java.util.List;

@Mapper
public interface ReadMapper {
    @Select("select * from user where neck_name=#{neckName}")
    UserData ifUserid(String neckName);

    @Select("select * from user where user_id=#{userId}")
    UserData getUserInform(Long userId);

    @Select("SELECT MAX(user_id) AS userId FROM user ")
    Long getMaxUserid();

    @Select("SELECT MAX(post_id) AS postId FROM post ")
    Long getMaxPostid();

    @Select("SELECT MAX(creation_id) AS creationId FROM creation ")
    Long getMaxCreationid();

    @Select("select * from post")
    List<PostData> getAllPosts();

    @Select("SELECT * FROM post WHERE tag = #{tag} AND subtag LIKE CONCAT('%', #{subtag}, '%')")
    List<PostData> getTargetPosts(String tag, String subtag);

    @Select("SELECT * FROM creation WHERE inform LIKE CONCAT('%', #{searchWord}, '%')")
    List<CreationData> getTargetCreations(String searchWord);

    @Select("select * from creation")
    List<CreationData> getAllCreations();

    @Select("select * from lost")
    List<LostData> getAllLosts();


    @Select("select * from post where post_id=#{postId}")
    PostData getAPost(Long postId);

    @Select("select * from lost where lostcreation_id=#{lostCreationId}")
    LostData getALost(Long lostCreationId);

    @Select("select * from creation where creation_id=#{creationId}")
    CreationData getACreation(Long creationId);

    @Select("select neck_name from user where user_id=#{userId}")
    String getNeckName(Long userId);

    @Select("select avatar from user where user_id=#{userId}")
    String getAvatar(Long userId);


    @Select("SELECT COUNT(*) AS favouriteNum FROM post_favourite WHERE post_id=#{postId}")
    Long getPostFavouriteNum(Long postId);

    @Select("SELECT COUNT(*) AS commentNum FROM post_comment WHERE post_id=#{postId}")
    Long getPostCommentNum(Long postId);



    @Select("SELECT COUNT(*) AS favouriteNum FROM lost_favourite WHERE lostcreation_id=#{lostCreationId}")
    Long getLostFavouriteNum(Long lostCreationId);

    @Select("SELECT COUNT(*) AS commentNum FROM lost_comment WHERE lostcreation_id=#{lostCreationId}")
    Long getLostCommentNum(Long lostCreationId);


    @Select("select * from post_comment where post_id=#{postId}")
    List<PostCommentData> getPostComment(Long postId);

    @Select("select * from lost_comment where lostcreation_id=#{lostCreationId}")
    List<LostCommentData> getLostComment(Long lostCreationId);

    @Select("select * from creation_comment where creation_id=#{creationId}")
    List<CreationCommentData> getCreationComment(Long creationId);

    @Select("SELECT MAX(comment_id) AS commentId FROM post_comment ")
    Long getMaxPostCommentId();


    @Select("SELECT MAX(comment_id) AS commentId FROM lost_comment ")
    Long getMaxLostCommentId();


    @Select("SELECT MAX(comment_id) AS commentId FROM creation_comment ")
    Long getMaxCreationCommentId();

    @Select("SELECT COUNT(*) AS favouriteNum FROM post_favourite WHERE post_id=#{postId} and user_id=#{userId}")
    Long getPostFavourite(Long postId ,Long userId);

    @Select("SELECT COUNT(*) AS favouriteNum FROM lost_favourite WHERE lostCreation_id=#{lostCreationId} and user_id=#{userId}")
    Long getLostFavourite(Long lostCreationId ,Long userId);

    @Select("SELECT COUNT(*) AS favouriteNum FROM creation_favourite WHERE creation_id=#{creationId} and user_id=#{userId}")
    Long getCreationFavourite(Long creationId ,Long userId);

    @Select("SELECT COUNT(*) AS favouriteNum FROM creation_favourite WHERE creation_id=#{creationId}")
    Long getCreationFavouriteNum(Long creationId);

    @Select("SELECT COUNT(*) AS commentNum FROM creation_comment WHERE creation_id=#{creationId}")
    Long getCreationCommentNum(Long creationId);


}
