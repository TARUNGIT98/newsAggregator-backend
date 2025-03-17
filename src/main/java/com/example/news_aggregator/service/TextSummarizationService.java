package com.example.news_aggregator.service;

import org.springframework.stereotype.Service;

@Service
public class TextSummarizationService {

    public String summarizeText(String text) {
        if(text == null || text.isEmpty()){
            return "";
        }
        // Split text into sentences (using period followed by a space)
        String[] sentences = text.split("\\. ");
        if(sentences.length >= 2) {
            return sentences[0] + ". " + sentences[1] + ".";
        }
        return text; // Fallback: return original text if not enough sentences
    }
}
