package com.example.news_aggregator.controller;

import com.example.news_aggregator.model.UserPreference;
import com.example.news_aggregator.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService service;

    @PostMapping("/update/{userId}")
    public ResponseEntity<UserPreference> updatePreference(
            @PathVariable String userId,
            @RequestBody UserPreference preference) {
        // Ensure the preference object has the correct userId
        preference.setUserId(userId);
        UserPreference savedPreference = service.savePreference(preference);
        return ResponseEntity.ok(savedPreference);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreference> getPreference(@PathVariable String userId) {
        return service.getPreferenceByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/update/{userId}")
    public ResponseEntity<UserPreference> getUpdatedPreference(@PathVariable String userId) {
        return service.getPreferenceByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}