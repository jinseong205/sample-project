package com.example.sample.global.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

object RedisSerializerFactory {
    fun <T> createJacksonSerializer(clazz: Class<T>): Jackson2JsonRedisSerializer<T> {
        val objectMapper =
            ObjectMapper().apply {
                registerKotlinModule()
                findAndRegisterModules()
            }

        return Jackson2JsonRedisSerializer(objectMapper, clazz)
    }
}
