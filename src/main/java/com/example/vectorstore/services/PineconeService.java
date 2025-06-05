package com.example.vectorstore.services;

import org.openapitools.db_data.client.ApiException;
import org.springframework.stereotype.Service;

import com.example.vectorstore.components.PineconeComponent;
import com.example.vectorstore.data.PCFilter;

@Service
public class PineconeService {

    private PineconeComponent pineconeComponent;

    public PineconeService(PineconeComponent pineconeComponent) {
        this.pineconeComponent = pineconeComponent;
    }

    public String getAnswer(PCFilter pCFilter) {
        String results = null;
        try {
            results = pineconeComponent.getAnswer(pCFilter);
        } catch(ApiException apiEx) {
            System.err.println("API Exception occurred: " + apiEx.getMessage());
            apiEx.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exeption occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
        return results;
    }
}
