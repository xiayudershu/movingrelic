package org.xiayudeshu.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiayudeshu.mapper.ReadMapper;
import org.xiayudeshu.mapper.WriteMapper;
import org.xiayudeshu.pojo.dto.UserLogin;
import org.xiayudeshu.pojo.dto.UserSetup;
import org.xiayudeshu.pojo.entity.UserData;

import java.util.Objects;

@Service
public class LoginAndSetupService {
    @Autowired
    private ReadMapper readMapper;
    @Autowired
    private WriteMapper writeMapper;

    public Long UserLogin(UserLogin userLogin){

        UserData userData=readMapper.ifUserid(userLogin.getNeckName());
        if(userData==null){
            return -1L;
        }
        else{
            if(Objects.equals(userData.getPassword(), userLogin.getPassword())){
                return userData.getUserId();
            }
            return 0L;
        }

    }


    public Long UserSetup(UserSetup userSetup){
        UserData tempUser=readMapper.ifUserid(userSetup.getNeckName());
        if(tempUser!=null){
            return -1L;
        }
        else{
            Long currentId= readMapper.getMaxUserid();
            UserData userData=new UserData();
            userData.setUserId(currentId+1L);
            userData.setSex(userSetup.getSex());
            userData.setPhone(userSetup.getPhone());
            userData.setAvatar(userSetup.getAvatar());
            userData.setPassword(userSetup.getPassword());
            userData.setNeckName(userSetup.getNeckName());

            writeMapper.writeUser(userData.getNeckName(),
                    userData.getUserId(),
                    userData.getAvatar(),
                    userData.getPhone(),
                    userData.getSex(),
                    userData.getPassword());

            return userData.getUserId();
        }

    }




}
