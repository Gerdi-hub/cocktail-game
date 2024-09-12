package com.ridango.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CocktailResponse {
    @JsonProperty("drinks")
    private List<CocktailDto> cocktailDtos;
}
