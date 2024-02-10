package com.problem1.service;

import com.problem1.exception.ApiRequestException;
import com.problem1.model.Entry;
import com.problem1.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final RestTemplate restTemplate;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Map<String, String>> getAllEntriesByCategory(String category, String url) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map responseBody = response.getBody();
                List<Map<String, String>> allEntries = (List<Map<String, String>>) responseBody.get("entries");

                // Filter entries by category
                List<Map<String, String>> filteredEntries = new ArrayList<>();
                for (Map<String, String> entry : allEntries) {
                    if (entry.get("Category").equalsIgnoreCase(category)) {
                        Map<String, String> filteredEntry = Map.of("Title", entry.get("API"), "Description", entry.get("Description"));
                        filteredEntries.add(filteredEntry);
                    }
                }

                return filteredEntries;
            } else {
                throw new ApiRequestException("Failed to fetch entries from the API");
            }
        } catch (Exception e) {
            throw new ApiRequestException("Failed to fetch entries from the API", e);
        }
    }

    @Override
    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }
}
