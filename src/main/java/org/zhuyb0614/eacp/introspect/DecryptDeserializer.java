package org.zhuyb0614.eacp.introspect;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.zhuyb0614.eacp.spi.Encryptor;

import java.io.IOException;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 17:08
 */
@Slf4j
public class DecryptDeserializer extends StdScalarDeserializer<Object> {

    private final Encryptor encryptor;

    public DecryptDeserializer(Encryptor encryptor) {
        super(Object.class);
        this.encryptor = encryptor;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        log.info("before decrypt value {}", value);
        String decrypt = encryptor.decrypt(value);
        log.info("after decrypt value {}", decrypt);
        return decrypt;
    }
}
