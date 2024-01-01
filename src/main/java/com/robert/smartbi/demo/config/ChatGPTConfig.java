package com.robert.smartbi.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import retrofit2.Retrofit;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

import static com.theokanning.openai.service.OpenAiService.*;

@Configuration
public class ChatGPTConfig {
    @Value("${openai.token}")
    private String token;

    @Value("${openai.proxy.host:}")
    private String proxyHost;

    @Value("${openai.proxy.port:0}")
    private int proxyPort;

    @Bean
    public OpenAiService openAiService() {
        Duration timeout = Duration.ofSeconds(60);
        OkHttpClient.Builder clientBuilder = defaultClient(token, timeout).newBuilder();
        ObjectMapper mapper = defaultObjectMapper();
        if (StringUtils.hasLength(proxyHost)) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            clientBuilder = clientBuilder.proxy(proxy);
        }
        OkHttpClient client = clientBuilder.build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi openAiApi = retrofit.create(OpenAiApi.class);

        return new OpenAiService(openAiApi);
    }
}
