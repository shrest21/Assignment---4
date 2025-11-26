package com.flightapp.flightbooking.dto;

import lombok.Data;

@Data
public class SearchRequest {

    private String fromPlace;
    private String toPlace;
}
