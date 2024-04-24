package org.xiayudeshu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostDetail implements Serializable {
    private Long lostCreationId;
    private String name;
    private String inform;
    private List<String> picture;
    private String century;
    private LocalDate date;
    private Long favoriteNum;
    private Long commentNum;
    private List<LostDetail.comment> commentList;

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
