package com.ridango.game.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ridango.game.dto.CocktailDto;
import lombok.Data;

import java.util.List;

@Data
public class CocktailResponse {
    @JsonProperty("drinks")
    private List<CocktailDto> cocktailDtos;
}
