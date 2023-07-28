package org.zhuyb0614.eacp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zhuyb0614.eacp.spi.AESEncryptor;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 17:02
 */
public class AESEncryptorTest {

    private AESEncryptor aesEncryptor;

    @Before
    public void setUp() {
        EacpProperties eacpProperties = new EacpProperties();
        eacpProperties.setEncryptKey("1234567890qazwsx");
        aesEncryptor = new AESEncryptor(eacpProperties);
    }

    @Test
    public void encryptTest() {
        String encrypt = aesEncryptor.encrypt("1");
        Assert.assertTrue("B159A2377B93275A783B4B8BAF11AD78".equals(encrypt));
    }

    @Test
    public void decryptTest() {
        String decrypt = aesEncryptor.decrypt("B159A2377B93275A783B4B8BAF11AD78");
        Assert.assertTrue("1".equals(decrypt));
    }
}
