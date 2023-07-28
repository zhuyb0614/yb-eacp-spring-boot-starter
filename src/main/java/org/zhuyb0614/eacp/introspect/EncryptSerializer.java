package org.zhuyb0614.eacp.introspect;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import lombok.extern.slf4j.Slf4j;
import org.zhuyb0614.eacp.spi.Encryptor;

import java.io.IOException;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:05
 */
@Slf4j
public class EncryptSerializer extends StdScalarSerializer<Object> {
    private final Encryptor encryptor;

    public EncryptSerializer(Encryptor encryptor) {
        super(Object.class);
        this.encryptor = encryptor;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value instanceof String || value instanceof Integer || value instanceof Long) {
            String sourceStr = value.toString();
            log.info("before encrypt value {}", sourceStr);
            String encrypt = encryptor.encrypt(sourceStr);
            gen.writeString(encrypt);
            log.info("after encrypt value {}", encrypt);
        } else {
            throw new IllegalArgumentException("加密字段仅支持 String 类型");
        }

    }
}
