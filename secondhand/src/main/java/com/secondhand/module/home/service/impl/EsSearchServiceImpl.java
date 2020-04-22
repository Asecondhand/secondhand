package com.secondhand.module.home.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.secondhand.common.es.EsIndex;
import com.secondhand.module.home.service.EsSearchService;
import com.secondhand.module.home.vo.SearchVo;
import com.secondhand.module.home.vo.TipsVo;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.vo.UserVo;
import com.secondhand.util.exception.ServiceException;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public SearchVo searchUserOrProductByKeyword(String keyword) {
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
            searchResponse = productSendRequest(EsIndex.PRODUCTINDEX.getIndexName(), keyword,"productName");
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

    @Override
    public TipsVo getTips(String keyword) {
        TipsVo tipsVo =new TipsVo();
        List tips = new ArrayList();
        SearchRequest searchRequest = new SearchRequest("tips");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //前面为field,后面为匹配字段
        SuggestionBuilder termSuggestionBuilder =
                SuggestBuilders.completionSuggestion("tip.suggest").prefix(keyword);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("mysuggest", termSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(searchResponse != null){
            Suggest suggest = searchResponse.getSuggest();
            CompletionSuggestion completionSuggestion = suggest.getSuggestion("mysuggest");
            for (CompletionSuggestion.Entry entry : completionSuggestion.getEntries()) {
                for (CompletionSuggestion.Entry.Option option : entry) {
                    String suggestText = option.getText().string();
                    tips.add(suggestText);
                }
            }
        }
        if(tips.size() == 0){
            //可以使用kafka 异步上传
            Map<String,String> map = new HashMap<>(16);
            map.put("tip",keyword);
            try {
                restHighLevelClient.index(new IndexRequest("tips").source(map, XContentType.JSON),RequestOptions.DEFAULT );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tipsVo.setTip(tips);
        return tipsVo;
    }

    private SearchResponse sendRequest(String index,String keyword,String filed) throws IOException {
        //根据index 建议查询请求
        SearchRequest searchRequest = new SearchRequest(index);
        //查询条件的创建
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(keyword != null && keyword.length()!=0){
            searchSourceBuilder.query(QueryBuilders.matchQuery(filed,keyword));
        }
        searchRequest.source(searchSourceBuilder);
         return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }

    private SearchResponse productSendRequest(String index,String keyword,String filed) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(keyword!=null && keyword.length()!=0){
            searchSourceBuilder.query(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery(filed,keyword)).should(QueryBuilders.matchQuery("productTag",keyword)));

        }
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
