package org.zhuyb0614.eacp.spi;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:12
 */
public interface Encryptor {
    String encrypt(String sourceStr);

    String decrypt(String encryptStr);
}
