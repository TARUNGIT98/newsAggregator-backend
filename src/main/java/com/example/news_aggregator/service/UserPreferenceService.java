package com.example.news_aggregator.service;

import com.example.news_aggregator.model.UserPreference;
import com.example.news_aggregator.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service // Spring-managed bean, allowing it to be automatically injected into other components where needed.
public class UserPreferenceService {

    //    This injects the UserPreferenceRepository, which is responsible for database operations on UserPreference entities.
//    @Autowired ensures that Spring automatically provides an instance of UserPreferenceRepository.

    @Autowired
    private UserPreferenceRepository repository;

    // This method saves a UserPreference object to the DB
    // which either saves/updates using .save

    public UserPreference savePreference(UserPreference preference)
    {
        return repository.save(preference);
    }


    public Optional<UserPreference> getPreferenceByUserId(String userId){
        return repository.findByUserId(userId);
    }
}
