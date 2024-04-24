package org.xiayudeshu.pojo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPost implements Serializable{
    private List<String> pictures;
    private String title;
    private String content;
    private Long userId;
    private String tag;
    private String subtag;

}
