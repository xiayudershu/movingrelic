package org.xiayudeshu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.DeletMapper;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.*;
import org.xiayudeshu.pojo.entity.LostCommentData;
import org.xiayudeshu.pojo.entity.LostData;
import org.xiayudeshu.pojo.entity.PostCommentData;
import org.xiayudeshu.pojo.entity.PostData;
import org.xiayudeshu.pojo.vo.LostDetail;
import org.xiayudeshu.pojo.vo.Losts;
import org.xiayudeshu.pojo.vo.PostDetail;
import org.xiayudeshu.pojo.vo.Posts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LostService {
    @Autowired
    private ReadMapper readMapper;

    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private DeletMapper deletMapper;


    public List<Losts> GetLosts(){
        List<LostData> rawLosts=readMapper.getAllLosts();
        List<Losts> losts=new ArrayList<>();
        for (LostData rawData : rawLosts){
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
        return losts;
    }


    public LostDetail getLostDetail(Long lostCreationId){
        LostDetail lostDetail=new LostDetail();
        LostData lostData=readMapper.getALost(lostCreationId);

        List<String> pictureList = Arrays.asList(lostData.getPictures().split(","));
        lostDetail.setLostCreationId(lostCreationId);
        lostDetail.setName(lostData.getName());
        lostDetail.setDate(lostData.getDate());
        lostDetail.setCentury(lostData.getCentury());
        lostDetail.setInform(lostData.getInform());
        lostDetail.setPicture(pictureList);
        lostDetail.setFavoriteNum(readMapper.getLostFavouriteNum(lostCreationId));
        lostDetail.setCommentNum(readMapper.getLostCommentNum(lostCreationId));


        List<LostDetail.comment> commentList=new ArrayList<>();
        List<LostCommentData> lostCommentDataList=readMapper.getLostComment(lostCreationId);

        for(LostCommentData lostCommentData:lostCommentDataList){
            LostDetail.comment comment=new LostDetail.comment();
            comment.setTime(lostCommentData.getTime());
            comment.setCommentId(lostCommentData.getCommentId());
            comment.setParentId(lostCommentData.getParentId());
            comment.setComment(lostCommentData.getComment());
            comment.setNeckName(readMapper.getNeckName(lostCommentData.getUserId()));
            comment.setAvatar(readMapper.getAvatar(lostCommentData.getUserId()));
            commentList.add(comment);
        }
        lostDetail.setCommentList(commentList);

        return lostDetail;
    }

    public Long AddLostComment(AddLostComment addLostComment){

        LostCommentData lostCommentData=new LostCommentData();
        lostCommentData.setCommentId(readMapper.getMaxLostCommentId()+1L);
        lostCommentData.setComment(addLostComment.getComment());
        lostCommentData.setLostCreationId(addLostComment.getLostCreationId());
        lostCommentData.setUserId(addLostComment.getUserId());
        lostCommentData.setParentId(addLostComment.getParentId());
        lostCommentData.setTime(LocalDateTime.now());

        writeMapper.writeLostComment(lostCommentData.getLostCreationId(),
                lostCommentData.getUserId(),
                lostCommentData.getCommentId(),
                lostCommentData.getComment(),
                lostCommentData.getTime(),
                lostCommentData.getParentId());

        return lostCommentData.getCommentId();
    }


    public void LikeLost(LikeLost likeLost){
        if(readMapper.getLostFavourite(likeLost.getLostCreationId(),likeLost.getUserId())==0){
            writeMapper.LikeLost(likeLost.getLostCreationId(),likeLost.getUserId());
        }
    }
    public void DislikeLost(LikeLost likeLost){
        if(readMapper.getLostFavourite(likeLost.getLostCreationId(),likeLost.getUserId())==1){
            deletMapper.DislikeLost(likeLost.getLostCreationId(),likeLost.getUserId());
        }


    }
}
