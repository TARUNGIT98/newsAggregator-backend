package com.example.news_aggregator.controller;

import com.example.news_aggregator.model.Article;
import com.example.news_aggregator.service.NewsFetcherService;
import com.example.news_aggregator.service.TextSummarizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsFetcherService newsFetcherService;

    @Autowired
    private TextSummarizationService textSummarizationService;

    @GetMapping
    public ResponseEntity<List<Article>> getNews(@RequestParam String category) {
        List<Article> articles = newsFetcherService.fetchNewsByCategory(category);
        if (articles != null) {
            // For each article, generate and set a summary
            articles.forEach(article -> {
                String summary = textSummarizationService.summarizeText(article.getDescription());
                article.setSummary(summary);
            });
            return ResponseEntity.ok(articles);
        }
        return ResponseEntity.notFound().build();
    }
}
