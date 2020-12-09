package com.nps.tacocloud.controller.design;

import com.nps.tacocloud.dao.IngredientRepository;
import com.nps.tacocloud.dao.OrderRepository;
import com.nps.tacocloud.dao.TacoARepository;
import com.nps.tacocloud.dao.TacoRepository;
import com.nps.tacocloud.data.Ingredient;
import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.Taco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by peishen.nie on 2020/7/13.
 */


@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    private final TacoARepository tacoARepository;

    private final OrderRepository orderRepository;



    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoARepository tacoARepository, OrderRepository orderRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoARepository = tacoARepository;
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
        Taco taco = tacoARepository.save(design);
        order.addDesign(taco);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(1, 12, Sort.by("creatAt").descending());
        return tacoARepository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optional = tacoARepository.findById(id);
        if(optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Taco(), HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoARepository.save(taco);
    }
}
