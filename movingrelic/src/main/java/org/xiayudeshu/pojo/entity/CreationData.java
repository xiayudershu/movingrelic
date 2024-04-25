package org.xiayudeshu.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreationData implements Serializable {
    private Long creationId;
    private String pictures;
    private String title;
    private String inform;
    private Long userId;
    private LocalDateTime time;
    private Boolean ifPublic;
    private Long favouriteNum;
}
