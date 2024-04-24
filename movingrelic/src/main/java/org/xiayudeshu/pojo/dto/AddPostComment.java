package org.xiayudeshu.pojo.dto;


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
public class AddPostComment implements Serializable {
    private Long userId;
    private Long postId;
    private String comment;
    private Long parentId;
}
