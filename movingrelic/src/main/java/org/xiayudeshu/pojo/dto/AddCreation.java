package org.xiayudeshu.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCreation implements Serializable {
    private List<String> pictures;
    private String title;
    private String inform;
    private Long userId;
    private Boolean ifPublic;
}
