package org.openmbee.sdvc.crud.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.openmbee.sdvc.crud.config.DbContextHolder;
import org.openmbee.sdvc.json.BaseJson;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BaseElasticDAOImpl {

    protected RestHighLevelClient client;

    @Autowired
    public void setRestHighLevelClient(@Qualifier("clientElastic") RestHighLevelClient client) {
        this.client = client;
    }

    public String getIndex() {
        return DbContextHolder.getContext().getIndex();
    }

    public Map<String, Object> findByIndexId(String index, String indexId) {
        return null;
    }

    public List<Map<String, Object>> findByIndexIds(String index, Set<String> indexIds) {
        List<Map<String, Object>> maps = new ArrayList<>();
        int i = 97;
        for (String eid : indexIds) {
            BaseJson json = new BaseJson();
            json.setIndexId(eid);
            json.setModified("2018-12-08T01:25:00.117-0700");
            json.setId(Character.toString((char) i));
            json.setName(json.getId());
            maps.add(json);
            i++;
        }
        /*
        BaseJson baseJson = new BaseJson();
        baseJson.setId("testing");
        baseJson.setName("element1");
        baseJson.getIndexId("8a1ee2ef-078f-4f3f-ae89-66fb5e9e7bba");
        baseJson.setModified("2015-07-04T12:08:56.235-0700");

        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(baseJson);
        */
        return maps;
    }

    public void createAll(String index, Collection<? extends BaseJson> jsons) throws IOException {
        BulkRequest bulkIndex = new BulkRequest();
        for (BaseJson json : jsons) {
            bulkIndex.add(new IndexRequest((index)).source(json));
        }
        client.bulk(bulkIndex, RequestOptions.DEFAULT);
    }

    public void create(String index, BaseJson json) throws IOException {
        client.index(new IndexRequest(index).source(json), RequestOptions.DEFAULT);
    }
}
