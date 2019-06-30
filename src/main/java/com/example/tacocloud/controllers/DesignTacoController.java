package com.example.tacocloud.controllers;

import com.example.tacocloud.Ingredient;
import com.example.tacocloud.Ingredient.Type;
import com.example.tacocloud.Order;
import com.example.tacocloud.Taco;
import com.example.tacocloud.repositories.ingredients.IngredientRepository;
import com.example.tacocloud.repositories.tacos.JdbcTacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    @Autowired
    private static IngredientRepository ingredientRepo;

    private final JdbcTacoRepository tacoRepository;
    @Autowired
    public DesignTacoController(JdbcTacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        initialize(model);
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design,
                                 Errors errors,
                                 Model model,
                                 @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            initialize(model);
            return "design";
        }
        Taco saved = tacoRepository.save(design);
        order.setTaco(saved);
        log.info("Check of working: " + design.getIngredients());
        log.info("Processing design" + design);
        return "redirect:/orders/current";
    }
    private static void initialize(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), Ingredient.filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());
    }
}
