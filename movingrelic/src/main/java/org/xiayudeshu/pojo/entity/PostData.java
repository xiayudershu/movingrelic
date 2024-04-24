package org.xiayudeshu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostData implements Serializable{
    private Long postId;
    private String pictures;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime time;
    private String tag;
    private String subtag;



}
