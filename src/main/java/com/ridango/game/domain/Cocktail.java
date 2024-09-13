package com.ridango.game.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Cocktail {
    private Long id;
    private String name;
    private String instructions;
    private String glass;
    private String category;
    private List<String> ingredients = new ArrayList<>();

}
