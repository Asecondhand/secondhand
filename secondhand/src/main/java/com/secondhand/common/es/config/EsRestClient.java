package com.secondhand.common.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-18 11:17
 **/
@Component
public class EsRestClient {
    //创建restClient
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("8.129.20.135", 9200, "http")));
    }
    //什么时候关闭 client

}
