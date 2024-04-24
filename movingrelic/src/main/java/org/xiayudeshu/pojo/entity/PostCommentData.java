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
public class PostCommentData implements Serializable {
    private Long commentId;
    private Long userId;
    private Long postId;
    private String comment;
    private LocalDateTime time;
    private Long parentId;


}
