package com.example.news_aggregator.service;

import com.example.news_aggregator.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ai.djl.translate.TranslateException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIFilteringService {

    @Autowired
    private TextEmbeddingService textEmbeddingService;

    public List<Article> filterArticlesByUserPreferences(List<Article> articles, String userPreferences) {
        List<Article> filteredArticles = new ArrayList<>();
        try {
            // Compute embedding for user preferences.
            float[] userEmbedding = textEmbeddingService.getEmbedding(userPreferences);
            for (Article article : articles) {
                // Combine title and description as the article's content.
                String content = article.getTitle() + ". " + article.getDescription();
                float[] articleEmbedding = textEmbeddingService.getEmbedding(content);
                double distance = CosineDistanceUtil.compute(userEmbedding, articleEmbedding);
                double similarity = 1 - distance; // Higher similarity means more relevance.
                // Adjust the threshold (e.g., 0.7) as needed.
                if (similarity > 0.7) {
                    filteredArticles.add(article);
                }
            }
        } catch (TranslateException e) {
            e.printStackTrace();
            // In case of errors, return the original list.
            return articles;
        }
        return filteredArticles;
    }
}
