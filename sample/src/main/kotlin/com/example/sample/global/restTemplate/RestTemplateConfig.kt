package com.example.sample.global.restTemplate

import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.core5.http.io.SocketConfig
import org.apache.hc.core5.util.Timeout
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {
    @Bean(name = ["aServiceRestTemplate"])
    fun aServiceRestTemplate(): RestTemplate = createRestTemplate(maxTotal = 2, maxPerRoute = 2, timeoutSeconds = 5)

    @Bean(name = ["bServiceRestTemplate"])
    fun bServiceRestTemplate(): RestTemplate = createRestTemplate(maxTotal = 2, maxPerRoute = 2, timeoutSeconds = 5)

    private fun createRestTemplate(
        maxTotal: Int,
        maxPerRoute: Int,
        timeoutSeconds: Long,
    ): RestTemplate {
        val timeout = Timeout.ofSeconds(timeoutSeconds)

        val socketConfig =
            SocketConfig
                .custom()
                .setSoTimeout(timeout)
                .build()

        val connManager =
            PoolingHttpClientConnectionManager().apply {
                this.maxTotal = maxTotal
                this.defaultMaxPerRoute = maxPerRoute
                this.defaultSocketConfig = socketConfig
            }

        val requestConfig =
            RequestConfig
                .custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(0)) // 커넥션 풀에서 가져오기 대기 시간
                .setResponseTimeout(timeout) // 전체 응답 대기 시간
                .build()

        val httpClient =
            HttpClients
                .custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .disableAutomaticRetries()
                .build()

        return RestTemplate(HttpComponentsClientHttpRequestFactory(httpClient))
    }
}
