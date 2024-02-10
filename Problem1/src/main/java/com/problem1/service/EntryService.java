package com.problem1.service;

import com.problem1.model.Entry;

import java.util.List;
import java.util.Map;

public interface EntryService {
    List<Map<String, String>> getAllEntriesByCategory(String category, String url);

    Entry saveEntry(Entry entry);
}