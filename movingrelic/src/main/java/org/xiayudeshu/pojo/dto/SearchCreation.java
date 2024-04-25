package org.xiayudeshu.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCreation implements Serializable {
    private Long userId;
    private String searchWord;
    private Integer pageSize;
    private Integer currentPage;
}
