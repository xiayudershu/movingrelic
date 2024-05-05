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

    @Select("SELECT * FROM post WHERE tag = #{tag} AND subtag LIKE CONCAT('%', #{subtag}, '%') ORDER BY favouriteNum DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<PostData> getTargetPosts(String tag, String subtag,Integer pageSize,Integer offset);

    @Select("SELECT * FROM creation WHERE inform LIKE CONCAT('%', #{searchWord}, '%') AND if_public=true ORDER BY favouriteNum DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<CreationData> getTargetCreations(String searchWord, Integer pageSize, Integer offset);

    @Select("select * from creation")
    List<CreationData> getAllCreations();

    @Select("select * from lost")
    List<LostData> getAllLosts();

    @Select("SELECT * FROM lost WHERE inform LIKE CONCAT('%', #{searchWord}, '%') OR century LIKE CONCAT('%', #{searchWord}, '%') ORDER BY favouriteNum DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<LostData> getTargetLosts(String searchWord,Integer pageSize,Integer offset);




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

    @Select("SELECT post_id FROM post_favourite WHERE user_id=#{userId}")
    List<Long> getPostUserFavourite(Long userId);

    @Select("SELECT lostcreation_id FROM lost_favourite WHERE user_id=#{userId}")
    List<Long> getLostUserFavourite(Long userId);

    @Select("SELECT creation_id FROM creation_favourite WHERE user_id=#{userId}")
    List<Long> getCreationUserFavourite(Long userId);




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
