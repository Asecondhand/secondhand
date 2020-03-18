package com.secondhand.module.home.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.secondhand.common.es.EsIndex;
import com.secondhand.module.home.service.EsSearchService;
import com.secondhand.module.home.vo.SearchVo;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.vo.UserVo;
import com.secondhand.util.exception.ServiceException;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-18 17:02
 **/
@Service
public class EsSearchServiceImpl implements EsSearchService {

    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Override
    public SearchVo SearchUserOrProductByKeyword(String keyword) {
        //form
        //size
        //sort
        SearchVo searchVo = null;
        SearchResponse searchResponse= null;
        try {
            searchResponse = sendRequest(EsIndex.USERINDEX.getIndexName(), keyword,"uname");
             SearchHits hits = searchResponse.getHits();
            TotalHits totalHits = hits.getTotalHits();
// the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;
// whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
            SearchHit[] searchHits = hits.getHits();
            searchVo =new SearchVo();
            for (SearchHit hit: searchHits) {
                String index = hit.getIndex();
                String id = hit.getId();
                float score = hit.getScore();
                Object userAttr =  JSON.parse(hit.getSourceAsString());
                searchVo.getUsers().add(userAttr);
            }

        } catch (IOException e) {
            throw  new ServiceException("es查询失败!");
        }
        try {
            searchResponse = sendRequest(EsIndex.PRODUCTINDEX.getIndexName(), keyword,"productName");
            SearchHits hits = searchResponse.getHits();
            TotalHits totalHits = hits.getTotalHits();
           // the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;
           // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit: searchHits) {
                String index = hit.getIndex();
                String id = hit.getId();
                float score = hit.getScore();
                Object userAttr =  JSON.parse(hit.getSourceAsString());
                searchVo.getProduct().add(userAttr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchVo;
    }
    private SearchResponse sendRequest(String index,String keyword,String filed) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(filed,keyword).fuzziness(Fuzziness.AUTO));
        searchRequest.source(searchSourceBuilder);
         return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }

}
