package com.tq.netty.learnnetty.serialize;

import com.alibaba.fastjson.JSON;


public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> cls, byte[] bytes) {
        return JSON.parseObject(bytes,cls);
    }
}
