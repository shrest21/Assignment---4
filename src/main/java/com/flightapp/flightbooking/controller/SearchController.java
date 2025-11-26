package com.flightapp.flightbooking.controller;

import com.flightapp.flightbooking.dto.SearchRequest;
import com.flightapp.flightbooking.entity.Inventory;
import com.flightapp.flightbooking.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<Inventory> searchFlights(
            @RequestParam String fromPlace,
            @RequestParam String toPlace
    ) {
        SearchRequest req = new SearchRequest();
        req.setFromPlace(fromPlace);
        req.setToPlace(toPlace);

        return searchService.search(req);
    }
}
