package org.zhuyb0614.eacp.introspect;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;
import org.zhuyb0614.eacp.anno.EncryptField;

@Slf4j
public class EncryptFieldAnnotationIntrospect extends NopAnnotationIntrospector {

    private final EncryptSerializer encryptSerializer;
    private final DecryptDeserializer decryptDeserializer;

    public EncryptFieldAnnotationIntrospect(EncryptSerializer encryptSerializer, DecryptDeserializer decryptDeserializer) {
        this.encryptSerializer = encryptSerializer;
        this.decryptDeserializer = decryptDeserializer;
    }

    @Override
    public Object findSerializer(Annotated am) {
        EncryptField annotation = am.getAnnotation(EncryptField.class);
        if (annotation != null) {
            return encryptSerializer;
        }
        return super.findSerializer(am);
    }

    @Override
    public Object findDeserializer(Annotated am) {
        EncryptField annotation = am.getAnnotation(EncryptField.class);
        if (annotation != null) {
            return decryptDeserializer;
        }
        return super.findDeserializer(am);
    }
}