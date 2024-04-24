package org.xiayudeshu.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.DeletMapper;
import org.xiayudeshu.mapper.EditMapper;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.EditUserInform;
import org.xiayudeshu.pojo.entity.UserData;

@Service
public class UserService {
    @Autowired
    private ReadMapper readMapper;

    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private EditMapper editMapper;


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


}
