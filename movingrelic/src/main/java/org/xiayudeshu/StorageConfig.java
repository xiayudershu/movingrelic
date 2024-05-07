package org.xiayudeshu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "storage")
@Configuration("storage")
public class StorageConfig {
    private String path;
    private String aipath;
}
