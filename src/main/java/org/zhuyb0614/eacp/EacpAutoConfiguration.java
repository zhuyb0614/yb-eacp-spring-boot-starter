package org.zhuyb0614.eacp;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.zhuyb0614.eacp.introspect.DecryptDeserializer;
import org.zhuyb0614.eacp.introspect.EncryptFieldAnnotationIntrospect;
import org.zhuyb0614.eacp.introspect.EncryptSerializer;
import org.zhuyb0614.eacp.spi.AESEncryptor;
import org.zhuyb0614.eacp.spi.Encryptor;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:22
 */
@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties(EacpProperties.class)
@ConditionalOnProperty(value = "yb.eacp.open-switch", havingValue = "true", matchIfMissing = true)
public class EacpAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Encryptor encryptor(EacpProperties eacpProperties) {
        return new AESEncryptor(eacpProperties);
    }

    @Bean
    public EncryptSerializer encryptSerializer(Encryptor encryptor) {
        return new EncryptSerializer(encryptor);
    }

    @Bean
    public DecryptDeserializer decryptDeserializer(Encryptor encryptor) {
        return new DecryptDeserializer(encryptor);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
    @SuppressWarnings("all")
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder, EncryptSerializer encryptSerializer, DecryptDeserializer decryptDeserializer) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
        AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new EncryptFieldAnnotationIntrospect(encryptSerializer, decryptDeserializer));
        objectMapper.setAnnotationIntrospector(newAi);
        return objectMapper;
    }

}
