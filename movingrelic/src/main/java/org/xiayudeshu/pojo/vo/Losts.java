package org.xiayudeshu.pojo.vo;

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
public class Losts implements Serializable {
    private Long lostCreationId;
    private String name;
    private String picture;
    private String century;
    private LocalDate date;
    private Long favoriteNum;

}
