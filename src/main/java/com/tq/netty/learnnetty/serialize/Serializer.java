package com.tq.netty.learnnetty.serialize;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> cls,byte[] bytes);
}
