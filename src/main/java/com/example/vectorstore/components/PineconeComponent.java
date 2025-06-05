package com.example.vectorstore.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openapitools.db_data.client.ApiException;
import org.openapitools.db_data.client.model.SearchRecordsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.vectorstore.data.PCFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.pinecone.clients.Index;

@Component
public class PineconeComponent {

    private final Index pineconeIndex;

    @Value("${pc.namespace}")
    private String nameSpace;

    public PineconeComponent(Index pineconeIndex) {
        this.pineconeIndex = pineconeIndex;
    }

    public String getAnswer(PCFilter pcFilter) throws ApiException, JsonProcessingException {
        String query = "Facility has not been modified since creation";
        List<String> fields = new ArrayList<>();
        fields.add("FACILITY_CODE");
        fields.add("FACILITY_ORDER");
        fields.add("PROCESS_TYPE");

        Map<String, Object> filter = new HashMap<>();
        if (pcFilter != null && StringUtils.hasText(pcFilter.getFacilityCode())) {
            filter.put("FACILITY_CODE", pcFilter.getFacilityCode());
        }

        if (pcFilter != null && StringUtils.hasText(pcFilter.getFacilityOrder())) {
            filter.put("FACILITY_ORDER", pcFilter.getFacilityOrder());
        }
        
        if (pcFilter != null && StringUtils.hasText(pcFilter.getFaiclityType())) {
            filter.put("FACILITY_TYPE", pcFilter.getFaiclityType());
        }

        SearchRecordsResponse recordsResponse = pineconeIndex.searchRecordsByText(query, nameSpace, fields, 2, filter, null);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(recordsResponse);
        System.out.println("json: " + json);

        return json;
    }
}
