package com.example.news_aggregator.service;

import com.example.news_aggregator.model.Article;
import com.example.news_aggregator.model.NewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class NewsFetcherService {

    @Value("${newsapi.apiKey}")
    private String apiKey;

    @Value("${newsapi.baseUrl}")
    private String baseUrl;

    public List<Article> fetchNewsByCategoryAndCountry(String category , String country) {
        // Construct the URL with category and API key
        String url = String.format("%s?category=%s&country=%s&apiKey=%s", baseUrl, category, country, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsResponse> responseEntity = restTemplate.getForEntity(url, NewsResponse.class);

        NewsResponse newsResponse = responseEntity.getBody();
        if (newsResponse != null && "ok".equals(newsResponse.getStatus())) {
            return newsResponse.getArticles();
        }
        return null;
    }
}
