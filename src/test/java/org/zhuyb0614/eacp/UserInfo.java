package org.zhuyb0614.eacp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zhuyb0614.eacp.anno.EncryptField;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:38
 */
@Data
public class UserInfo {
    @EncryptField
    @ApiModelProperty(example = "B159A2377B93275A783B4B8BAF11AD78")
    private String userId;
    private String username;
}
