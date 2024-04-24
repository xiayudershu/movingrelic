package org.xiayudeshu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable{
    private String neckName;
    private Long userId;
    private String avatar;
    private String phone;
    private String sex;
    private String password;

}
