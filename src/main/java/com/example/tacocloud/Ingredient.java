package com.example.tacocloud;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Ingredient {
    private static List<Ingredient> listOfTypes;
    private final String id;
    private final String name;
    private final Type Type;
    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
    public static List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        listOfTypes = new ArrayList<>();
        for(Ingredient s:ingredients){
            if(s.getType().toString().toLowerCase().equals(type.toString().toLowerCase()))
            listOfTypes.add(s);
        }
        return listOfTypes;
    }

}
