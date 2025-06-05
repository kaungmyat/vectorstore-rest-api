package com.example.vectorstore.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;
import io.pinecone.configs.PineconeConfig;
import io.pinecone.configs.PineconeConnection;

@Configuration
@PropertySource("classpath:application.secrets.properties")
public class PineconeCustomConfig {

    @Value("${pinecone.api.key}")
    private String apiKey;

    @Value("${pc.host.name}")
    private String hostName;

    @Value("${pc.index.name}")
    private String indexName;

    @Bean
    public Pinecone pineconeClient() {
        return new Pinecone.Builder(apiKey).build();
    }

    @Bean
    public PineconeConfig pineconeConfig() {
        PineconeConfig pcConfig = new PineconeConfig(apiKey);
        pcConfig.setHost(hostName);
        return pcConfig;
    }

    @Bean
    public PineconeConnection pineconeConnection() {
        return new PineconeConnection(pineconeConfig());
    }

    @Bean
    public Index pineconeIndex() {
        return new Index(pineconeConfig(), pineconeConnection(), indexName);
    }

}
