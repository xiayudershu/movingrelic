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
public class PostDetail implements Serializable {
    private Long postId;
    private List<String> picture;
    private String content;
    private String title;
    private String avatar;
    private String neckName;
    private Long favoriteNum;
    private Long commentNum;
    private LocalDateTime time;
    private String tag;
    private List<String> subtag;

    private List<comment> commentList;

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
