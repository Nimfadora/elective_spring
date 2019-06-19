package com.vasylieva.elective.service;

import com.google.common.collect.EvictingQueue;
import com.vasylieva.elective.model.status.Language;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HintService {

    private static final int QUEUE_SIZE = 1000;
    Queue<String> hintCache = EvictingQueue.create(QUEUE_SIZE);

    public void addHint(String searchString) {
        hintCache.add(searchString);
    }

    public Set<String> getHints(String searchString, String lang) {
        Iterator<String> hints =  hintCache.iterator();
        Set<String> topResults = new HashSet<>();
        while (hints.hasNext() && topResults.size() < 3) {
            String current = hints.next();
            if(current.contains(searchString)) {
                topResults.add(current);
            }
        }
        return topResults;
    }
}
