package org.xiayudeshu.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.DeletMapper;
import org.xiayudeshu.mapper.EditMapper;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.EditUserInform;
import org.xiayudeshu.pojo.entity.CreationData;
import org.xiayudeshu.pojo.entity.LostData;
import org.xiayudeshu.pojo.entity.PostData;
import org.xiayudeshu.pojo.entity.UserData;
import org.xiayudeshu.pojo.vo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private ReadMapper readMapper;

    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private EditMapper editMapper;

    public UserInform GetInform(Long userId){
        UserInform userInform=new UserInform();
        UserData userData=readMapper.getUserInform(userId);
        userInform.setSex(userData.getSex());
        userInform.setPhone(userData.getPhone());
        userInform.setPassword(userData.getPassword());
        userInform.setAvatar(userData.getAvatar());
        userInform.setNeckName(userData.getNeckName());

        return userInform;


    }


    public Long EditUserInform(EditUserInform editUserInform){
        UserData currentUserData=readMapper.getUserInform(editUserInform.getUserId());
        UserData userData=new UserData();
        userData.setUserId(editUserInform.getUserId());
        if(editUserInform.getNeckName()==null){
            userData.setNeckName(currentUserData.getNeckName());
        }
        else{
            userData.setNeckName(editUserInform.getNeckName());
        }

        if(editUserInform.getPassword()==null){
            userData.setPassword(currentUserData.getPassword());
        }
        else{
            userData.setPassword(editUserInform.getPassword());
        }

        if(editUserInform.getAvatar()==null){
            userData.setAvatar(currentUserData.getAvatar());
        }
        else{
            userData.setAvatar(editUserInform.getAvatar());
        }

        if(editUserInform.getSex()==null){
            userData.setSex(currentUserData.getSex());
        }
        else{
            userData.setSex(editUserInform.getSex());
        }

        if(editUserInform.getPhone()==null){
            userData.setPhone(currentUserData.getPhone());
        }
        else{
            userData.setPhone(editUserInform.getPhone());
        }


        editMapper.updateUserInform(userData);
        //System.out.print(userData);


        return 1L;
    }
    public Stars GetALLStars(Long userId){
        Stars stars=new Stars();
        List<Long> postIds=readMapper.getPostUserFavourite(userId);
        List<Long> lostIds=readMapper.getLostUserFavourite(userId);
        List<Long> creationIds=readMapper.getCreationUserFavourite(userId);
        //System.out.print(lostIds);
        List<Posts> posts=new ArrayList<>();
        List<Losts> losts=new ArrayList<>();
        List<Creations> creations=new ArrayList<>();
        for(Long postId : postIds){
            PostData rawData=readMapper.getAPost(postId);
            Posts post=new Posts();
            post.setPostId(rawData.getPostId());
            post.setTime(rawData.getTime());
            post.setTitle(rawData.getTitle());
            post.setTime(rawData.getTime());
            post.setTag(rawData.getTag());
            post.setFavoriteNum(rawData.getFavouriteNum());
            String[] pictureArray = rawData.getPictures().split(",");
            String firstPicture = pictureArray.length > 0 ? pictureArray[0] : "";
            post.setPicture(firstPicture);
            List<String> subArray = Arrays.asList(rawData.getSubtag().split(","));
            post.setSubtag(subArray);
            post.setNeckName(readMapper.getNeckName(rawData.getUserId()));
            post.setAvatar(readMapper.getAvatar(rawData.getUserId()));
            posts.add(post);

        }
        for(Long lostcreation : lostIds){
            LostData rawData=readMapper.getALost(lostcreation);
            Losts lost = new Losts();
            lost.setLostCreationId(rawData.getLostCreationId());
            lost.setDate(rawData.getDate());
            lost.setName(rawData.getName());
            lost.setCentury(rawData.getCentury());

            String[] pictureArray = rawData.getPictures().split(",");
            String firstPicture = pictureArray.length > 0 ? pictureArray[0] : ""; // 获取第一个元素
            lost.setPicture(firstPicture);
            lost.setFavoriteNum(readMapper.getLostFavouriteNum(rawData.getLostCreationId()));
            losts.add(lost);

        }
        for(Long creationId : creationIds){
            CreationData rawData=readMapper.getACreation(creationId);

                Creations creation = new Creations();
                creation.setCreationId(rawData.getCreationId());
                creation.setTitle(rawData.getTitle());
                creation.setTime(rawData.getTime());
                String[] pictureArray = rawData.getPictures().split(",");
                String firstPicture = pictureArray.length > 0 ? pictureArray[0] : ""; // 获取第一个元素
                creation.setPicture(firstPicture);

                creation.setNeckName(readMapper.getNeckName(rawData.getUserId()));
                creation.setAvatar(readMapper.getAvatar(rawData.getUserId()));
                creation.setFavoriteNum(readMapper.getCreationFavouriteNum(rawData.getCreationId()));

                creations.add(creation);


        }

        stars.setPost(posts);
        stars.setLost(losts);
        stars.setCreation(creations);

        return stars;

    }


}
