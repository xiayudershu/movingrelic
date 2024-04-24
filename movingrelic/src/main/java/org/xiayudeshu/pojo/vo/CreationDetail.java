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
public class CreationDetail implements Serializable {
    private Long creationId;
    private List<String> picture;
    private String inform;
    private String title;
    private String avatar;
    private String neckName;
    private Long favoriteNum;
    private Long commentNum;
    private LocalDateTime time;

    private List<CreationDetail.comment> commentList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class comment implements Serializable{
        private Long commentId;
        private Long parentId;
        private String avatar;
        private String neckName;
        private String comment;
        private LocalDateTime time;
    }
}
