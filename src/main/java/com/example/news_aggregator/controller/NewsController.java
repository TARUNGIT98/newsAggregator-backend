package com.example.news_aggregator.controller;

import com.example.news_aggregator.model.Article;
import com.example.news_aggregator.model.UserPreference;
import com.example.news_aggregator.service.NewsFetcherService;
import com.example.news_aggregator.service.TextSummarizationService;
import com.example.news_aggregator.service.UserPreferenceService;
import com.example.news_aggregator.service.AIFilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsFetcherService newsFetcherService;

    @Autowired
    private TextSummarizationService textSummarizationService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private AIFilteringService aiFilteringService;

    @GetMapping
    public ResponseEntity<List<Article>> getNews(@RequestParam String category,
                                                 @RequestParam(required = false) String userId) {
        List<Article> articles = newsFetcherService.fetchNewsByCategory(category);
        if (articles != null) {
            // Optionally summarize each article
            articles.forEach(article -> {
                String summary = textSummarizationService.summarizeText(article.getDescription());
                article.setSummary(summary);
            });

            // If a userId is provided, get the stored preferences and filter articles
            if (userId != null && !userId.isEmpty()) {
                Optional<UserPreference> optionalPreference = userPreferenceService.getPreferenceByUserId(userId);
                if (optionalPreference.isPresent()) {
                    String preferences = optionalPreference.get().getPreferredCategories();
                    articles = aiFilteringService.filterArticlesByUserPreferences(articles, preferences);
                }
            }
            return ResponseEntity.ok(articles);
        }
        return ResponseEntity.notFound().build();
    }
}
