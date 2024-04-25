package org.xiayudeshu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostData implements Serializable {

    private Long lostCreationId;
    private String name;
    private String inform;
    private String pictures;
    private String century;
    private LocalDate date;
    private Long favouriteNum;
}
