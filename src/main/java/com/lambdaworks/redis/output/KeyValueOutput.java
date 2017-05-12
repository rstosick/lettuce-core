/*
 * Copyright 2011-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambdaworks.redis.output;

import com.lambdaworks.redis.KeyValue;
import com.lambdaworks.redis.codec.RedisCodec;

import java.nio.ByteBuffer;

/**
 * Key-value pair output.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 *
 * @author Will Glozer
 */
public class KeyValueOutput<K, V> extends CommandOutput<K, V, KeyValue<K, V>> {
    private K key;

    public KeyValueOutput(RedisCodec<K, V> codec) {
        super(codec, null);
    }

    @Override
    public void set(ByteBuffer bytes) {
        if (bytes != null) {
            if (key == null) {
                key = codec.decodeKey(bytes);
            } else {
                V value = codec.decodeValue(bytes);
                output = new KeyValue<K, V>(key, value);
            }
        }
    }
}