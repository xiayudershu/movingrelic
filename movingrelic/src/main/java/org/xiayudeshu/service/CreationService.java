package org.xiayudeshu.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.DeletMapper;
import org.xiayudeshu.mapper.EditMapper;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.*;
import org.xiayudeshu.pojo.entity.CreationCommentData;
import org.xiayudeshu.pojo.entity.CreationData;
import org.xiayudeshu.pojo.entity.PostCommentData;
import org.xiayudeshu.pojo.entity.PostData;
import org.xiayudeshu.pojo.vo.CreationDetail;
import org.xiayudeshu.pojo.vo.Creations;
import org.xiayudeshu.pojo.vo.Losts;
import org.xiayudeshu.pojo.vo.PostDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreationService {
    @Autowired
    private ReadMapper readMapper;

    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private DeletMapper deletMapper;

    @Autowired
    private EditMapper editMapper;

    public Long AddCreation(AddCreation addCreation){
        Long currentId= readMapper.getMaxCreationid();
        CreationData creationData=new CreationData();
        creationData.setCreationId(currentId+1L);
        creationData.setUserId(addCreation.getUserId());
        creationData.setTime(LocalDateTime.now());
        creationData.setTitle(addCreation.getTitle());
        creationData.setInform(addCreation.getInform());
        creationData.setIfPublic(addCreation.getIfPublic());
        String pictures=String.join(",", addCreation.getPictures());
        creationData.setPictures(pictures);
        writeMapper.writeCreation(creationData.getCreationId(),
                creationData.getUserId(),
                creationData.getTitle(),
                creationData.getInform(),
                creationData.getPictures(),
                creationData.getTime(),
                creationData.getIfPublic());

        return creationData.getCreationId();

    }

    public List<Creations> GetCreations(){

        List<CreationData> rawCreations=readMapper.getAllCreations();
        List<Creations> creations=new ArrayList<>();
        for (CreationData rawData : rawCreations){
            if(rawData.getIfPublic()==true){
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
        }
        List<Creations> sortedCreations = creations.stream()
                .sorted(Comparator.comparing(Creations::getFavoriteNum).reversed())
                .collect(Collectors.toList());

        return sortedCreations;
    }

    public List<Creations> GetTargetCreations(SearchCreation searchCreation){
        Integer offset = (searchCreation.getCurrentPage() - 1) * searchCreation.getPageSize();
        List<CreationData> rawCreations=readMapper.getTargetCreations(searchCreation.getSearchWord(),searchCreation.getPageSize(),offset);
        List<Creations> creations=new ArrayList<>();
        for (CreationData rawData : rawCreations){
            if(rawData.getIfPublic()==true){
                Creations creation = new Creations();
                creation.setCreationId(rawData.getCreationId());
                creation.setTitle(rawData.getTitle());
                creation.setTime(rawData.getTime());
                creation.setFavoriteNum(rawData.getFavouriteNum());
                String[] pictureArray = rawData.getPictures().split(",");
                String firstPicture = pictureArray.length > 0 ? pictureArray[0] : ""; // 获取第一个元素
                creation.setPicture(firstPicture);

                creation.setNeckName(readMapper.getNeckName(rawData.getUserId()));
                creation.setAvatar(readMapper.getAvatar(rawData.getUserId()));


                creations.add(creation);
            }
        }


        return creations;
    }

    public CreationDetail getCreationDetail(Long creationId){
        CreationDetail creationDetail=new CreationDetail();
        CreationData creationData=readMapper.getACreation(creationId);

        List<String> pictureList = Arrays.asList(creationData.getPictures().split(","));

        creationDetail.setCreationId(creationId);
        creationDetail.setInform(creationData.getInform());
        creationDetail.setTitle(creationData.getTitle());
        creationDetail.setTime(creationData.getTime());
        creationDetail.setPicture(pictureList);
        creationDetail.setNeckName(readMapper.getNeckName(creationData.getUserId()));
        creationDetail.setAvatar(readMapper.getAvatar(creationData.getUserId()));
        creationDetail.setFavoriteNum(readMapper.getCreationCommentNum(creationId));
        creationDetail.setCommentNum(readMapper.getCreationCommentNum(creationId));


        List<CreationDetail.comment> commentList=new ArrayList<>();
        List<CreationCommentData> creationCommentDataList=readMapper.getCreationComment(creationId);
        for(CreationCommentData creationCommentData:creationCommentDataList){
            CreationDetail.comment comment=new CreationDetail.comment();
            comment.setTime(creationCommentData.getTime());
            comment.setCommentId(creationCommentData.getCommentId());
            comment.setParentId(creationCommentData.getParentId());
            comment.setComment(creationCommentData.getComment());
            comment.setNeckName(readMapper.getNeckName(creationCommentData.getUserId()));
            comment.setAvatar(readMapper.getAvatar(creationCommentData.getUserId()));

            commentList.add(comment);
        }
        creationDetail.setCommentList(commentList);

        return creationDetail;
    }

    public Long AddCreationComment(AddCreationComment addCreationComment){

        CreationCommentData creationCommentData=new CreationCommentData();
        creationCommentData.setCommentId(readMapper.getMaxCreationCommentId()+1L);
        creationCommentData.setComment(addCreationComment.getComment());
        creationCommentData.setCreationId(addCreationComment.getCreationId());
        creationCommentData.setUserId(addCreationComment.getUserId());
        creationCommentData.setParentId(addCreationComment.getParentId());
        creationCommentData.setTime(LocalDateTime.now());

        writeMapper.writeCreationComment(creationCommentData.getCreationId(),
                creationCommentData.getUserId(),
                creationCommentData.getCommentId(),
                creationCommentData.getComment(),
                creationCommentData.getTime(),
                creationCommentData.getParentId());

        return creationCommentData.getCommentId();
    }


    public void LikeCreation(LikeCreation likeCreation){
        if(readMapper.getCreationFavourite(likeCreation.getCreationId(),likeCreation.getUserId())==0){
            writeMapper.LikeCreation(likeCreation.getCreationId(),likeCreation.getUserId());
            editMapper.updateCreationFavouriteNum(likeCreation.getCreationId(), readMapper.getCreationFavouriteNum(likeCreation.getCreationId()));
        }
    }
    public void DislikeCreation(LikeCreation likeCreation){
        if(readMapper.getCreationFavourite(likeCreation.getCreationId(),likeCreation.getUserId())==1){
            deletMapper.DislikeCreation(likeCreation.getCreationId(),likeCreation.getUserId());
            editMapper.updateCreationFavouriteNum(likeCreation.getCreationId(), readMapper.getCreationFavouriteNum(likeCreation.getCreationId()));
        }


    }
}
