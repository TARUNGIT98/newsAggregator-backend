//package com.example.news_aggregator.controller;
//
//import com.example.news_aggregator.model.UserPreference;
//import com.example.news_aggregator.service.UserPreferenceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
////Marks this class as a REST API controller.
////All methods in this class return JSON responses.
////This is a combination of @Controller and @ResponseBody.
//@RestController
//// All routes inside this class will be prefixed with /api/preferences.
//@RequestMapping("/api/preferences")
//public class UserPreferenceController
//{
//
//    @Autowired
//    private UserPreferenceService service;
//
////    @PostMapping("/update") → Handles POST requests at /api/preferences/update.
////    @RequestBody UserPreference preference → Takes a JSON request body, converts it to a UserPreference object.
////    Calls service.savePreference(preference) to save or update the user preference.
////    Returns ResponseEntity.ok(savedPreference), which sends back the saved preference as a JSON response.
//    @PostMapping("/update")
//    public ResponseEntity<UserPreference> updatePreference(@RequestBody UserPreference preference)
//    {
//        UserPreference savedPreference = service.savePreference(preference);
//        return ResponseEntity.ok(savedPreference);
//    }
//
////    @GetMapping("/{userId}") → Handles GET requests at /api/preferences/{userId}.
////    @PathVariable String userId → Extracts {userId} from the URL.
////     Calls service.getPreferenceByUserId(userId), which returns an Optional<UserPreference>.
//
//
//        @GetMapping("/{userId}")
//        public ResponseEntity<UserPreference> getPreference(@PathVariable String userId)
//        {
//            return service.getPreferenceByUserId(userId)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//
//
//
//        }
//}
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