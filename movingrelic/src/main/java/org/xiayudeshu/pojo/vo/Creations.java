package org.xiayudeshu.pojo.vo;

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
public class Creations implements Serializable {
    private Long creationId;
    private String picture;
    private String title;
    private String avatar;
    private String neckName;
    private Long favoriteNum;
    private LocalDateTime time;
}
