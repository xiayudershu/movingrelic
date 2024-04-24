package org.xiayudeshu.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.DeletMapper;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.*;
import org.xiayudeshu.pojo.entity.PostCommentData;
import org.xiayudeshu.pojo.entity.PostData;
import org.xiayudeshu.pojo.vo.PostDetail;
import org.xiayudeshu.pojo.vo.Posts;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class PostService {
    @Autowired
    private ReadMapper readMapper;

    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private DeletMapper deletMapper;

    public Long AddPost(AddPost addPost){
        Long currentId= readMapper.getMaxPostid();
        PostData postData=new PostData();
        postData.setPostId(currentId+1L);
        postData.setUserId(addPost.getUserId());
        postData.setTime(LocalDateTime.now());
        postData.setTitle(addPost.getTitle());
        postData.setContent(addPost.getContent());
        String pictures=String.join(",", addPost.getPictures());
        postData.setPictures(pictures);
        postData.setTag(addPost.getTag());
        String subtags=String.join(",",addPost.getSubtag());
        postData.setSubtag(subtags);
        writeMapper.writePost(postData.getPostId(),
                postData.getUserId(),
                postData.getTitle(),
                postData.getContent(),
                postData.getPictures(),
                postData.getTime(),
                postData.getTag(),
                postData.getSubtag()
        );

        return postData.getPostId();

    }

    public List<Posts> GetPosts(){
        List<PostData> rawPosts=readMapper.getAllPosts();
        List<Posts> posts=new ArrayList<>();
        for (PostData rawData : rawPosts){
            Posts post = new Posts();
            post.setPostId(rawData.getPostId());
            post.setTime(rawData.getTime());
            post.setTitle(rawData.getTitle());
            post.setTime(rawData.getTime());
            post.setTag(rawData.getTag());
            String[] pictureArray = rawData.getPictures().split(",");
            String firstPicture = pictureArray.length > 0 ? pictureArray[0] : ""; // 获取第一个元素
            post.setPicture(firstPicture);
            List<String> subArray = Arrays.asList(rawData.getSubtag().split(","));
            post.setSubtag(subArray);

            post.setNeckName(readMapper.getNeckName(rawData.getUserId()));
            post.setAvatar(readMapper.getAvatar(rawData.getUserId()));
            post.setFavoriteNum(readMapper.getPostFavouriteNum(rawData.getPostId()));

            posts.add(post);

        }
        return posts;
    }


    public PostDetail getPostDetail(Long postId){
        PostDetail postDetail=new PostDetail();
        PostData postData=readMapper.getAPost(postId);


        List<String> pictureList = Arrays.asList(postData.getPictures().split(","));
        List<String> subtagList = Arrays.asList(postData.getSubtag().split(","));

        postDetail.setPostId(postId);
        postDetail.setContent(postData.getContent());
        postDetail.setTitle(postData.getTitle());
        postDetail.setTime(postData.getTime());
        postDetail.setPicture(pictureList);
        postDetail.setTag(postData.getTag());
        postDetail.setSubtag(subtagList);
        postDetail.setNeckName(readMapper.getNeckName(postData.getUserId()));
        postDetail.setAvatar(readMapper.getAvatar(postData.getUserId()));
        postDetail.setFavoriteNum(readMapper.getPostFavouriteNum(postId));
        postDetail.setCommentNum(readMapper.getPostCommentNum(postId));


        List<PostDetail.comment> commentList=new ArrayList<>();
        List<PostCommentData> postCommentDataList=readMapper.getPostComment(postId);
        for(PostCommentData postCommentData:postCommentDataList){
            PostDetail.comment comment=new PostDetail.comment();
            comment.setTime(postCommentData.getTime());
            comment.setCommentId(postCommentData.getCommentId());
            comment.setParentId(postCommentData.getParentId());
            comment.setComment(postCommentData.getComment());
            comment.setNeckName(readMapper.getNeckName(postCommentData.getUserId()));
            comment.setAvatar(readMapper.getAvatar(postCommentData.getUserId()));

            commentList.add(comment);
        }
        postDetail.setCommentList(commentList);

        return postDetail;
    }

    public Long AddPostComment(AddPostComment addPostComment){

        PostCommentData postCommentData=new PostCommentData();
        postCommentData.setCommentId(readMapper.getMaxPostCommentId()+1L);
        postCommentData.setComment(addPostComment.getComment());
        postCommentData.setPostId(addPostComment.getPostId());
        postCommentData.setUserId(addPostComment.getUserId());
        postCommentData.setParentId(addPostComment.getParentId());
        postCommentData.setTime(LocalDateTime.now());

        writeMapper.writePostComment(postCommentData.getPostId(),
                postCommentData.getUserId(),
                postCommentData.getCommentId(),
                postCommentData.getComment(),
                postCommentData.getTime(),
                postCommentData.getParentId());

        return postCommentData.getCommentId();
    }


    public void LikePost(LikePost likePost){
        if(readMapper.getPostFavourite(likePost.getPostId(),likePost.getUserId())==0){
            writeMapper.LikePost(likePost.getPostId(),likePost.getUserId());
        }
    }
    public void DislikePost(LikePost likePost){
        if(readMapper.getPostFavourite(likePost.getPostId(),likePost.getUserId())==1){
            deletMapper.DislikePost(likePost.getPostId(),likePost.getUserId());
        }


    }

}
