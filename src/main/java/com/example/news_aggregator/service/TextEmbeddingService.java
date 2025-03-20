package com.example.news_aggregator.service;

import ai.djl.Application;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.translate.TranslateException;
import ch.qos.logback.core.subst.Tokenizer;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class TextEmbeddingService
{
    private ZooModel<String,float[]> model;
    private Predictor<String,float[]> predictor;
    private HuggingFaceTokenizer tokenizer; // bert-base-uncased

    @PostConstruct
    public void init() throws ModelException , IOException
    {
        Criteria<String,float[]> criteria = Criteria.builder()
                .setTypes(String.class,float[].class)
                .optApplication(Application.NLP.TEXT_EMBEDDING)
                //.optFilter("model_name","sentence-transformers/all-MiniLM-L6-v2")
                .build();
        model = ModelZoo.loadModel(criteria);
        predictor = model.newPredictor();

        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    public float[] getEmbedding(String text) throws TranslateException
    {
        // Tokenize the input text (returns List<String>)
        List<String> tokenList = tokenizer.tokenize(text);

        // Convert List<String> to String[] explicitly
        String[] tokens = tokenList.toArray(new String[tokenList.size()]);

        // Print tokens for debugging
        System.out.println("Tokens: " + String.join(", ", tokens));

        // Convert tokens back to a single string before embedding (adjust if necessary)
        String tokenizedText = String.join(" ", tokens);

        // Get embeddings
        return predictor.predict(tokenizedText);
    }
}
