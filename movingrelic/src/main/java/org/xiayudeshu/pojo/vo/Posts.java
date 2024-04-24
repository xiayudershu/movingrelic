package org.xiayudeshu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts implements Serializable {
    private Long postId;
    private String picture;
    private String title;
    private String avatar;
    private String neckName;
    private Long favoriteNum;
    private LocalDateTime time;
    private String tag;
    private List<String> subtag;
}
