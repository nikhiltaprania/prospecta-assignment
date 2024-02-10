package com.problem1.controller;

import com.problem1.exception.ApiRequestException;
import com.problem1.model.Entry;
import com.problem1.service.EntryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EntryController {
    private final EntryService entryService;
    @Value("${api.url}")
    private String url;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/api/list")
    public ResponseEntity<List<Map<String, String>>> listEntriesByCategory(@RequestParam String category) {
        List<Map<String, String>> filteredEntries = entryService.getAllEntriesByCategory(category, url + "entries");
        return new ResponseEntity<>(filteredEntries, HttpStatus.OK);
    }

    @PostMapping("/api/save")
    public ResponseEntity<String> saveNewEntry(@RequestBody Map<String, Object> newEntryMap) {
        try {
            // Validate new entry
            if (!newEntryMap.containsKey("Title") || !newEntryMap.containsKey("Description") || !newEntryMap.containsKey("Category")) {
                return new ResponseEntity<>("Incomplete entry", HttpStatus.BAD_REQUEST);
            }

            // Create Entry object from the map
            Entry newEntry = new Entry();
            newEntry.setTitle((String) newEntryMap.get("Title"));
            newEntry.setDescription((String) newEntryMap.get("Description"));
            newEntry.setCategory((String) newEntryMap.get("Category"));

            // Save new entry to the database
            entryService.saveEntry(newEntry);

            return new ResponseEntity<>("Entry saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to process request", e);
        }
    }
}
