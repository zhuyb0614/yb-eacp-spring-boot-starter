package org.zhuyb0614.eacp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:14
 */
@Data
@SuppressWarnings("all")
@ConfigurationProperties(prefix = "yb.eacp")
public class EacpProperties {
    /**
     * 配置开关
     */
    private Boolean openSwitch;
    /**
     * 密钥
     */
    private String encryptKey;
}
