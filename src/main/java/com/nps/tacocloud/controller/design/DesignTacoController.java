package com.nps.tacocloud.controller.design;

import com.nps.tacocloud.dao.IngredientRepository;
import com.nps.tacocloud.dao.OrderRepository;
import com.nps.tacocloud.dao.TacoRepository;
import com.nps.tacocloud.data.Ingredient;
import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.Taco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peishen.nie on 2020/7/13.
 */


@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        logger.error("errors:" + errors);
        /*if(errors.hasErrors()){
            return "design";
        }*/
        logger.info("Processing design:" + design.getName());
        Taco taco = tacoRepository.save(design);
        order.addDesign(taco);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

}
