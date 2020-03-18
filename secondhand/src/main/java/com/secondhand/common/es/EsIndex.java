package com.secondhand.common.es;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
public enum  EsIndex {
    USERINDEX("user"),
    PRODUCTINDEX("product"),
    ;
    private String indexName;

    EsIndex(String indexName){
        this.indexName = indexName;
    }

}
