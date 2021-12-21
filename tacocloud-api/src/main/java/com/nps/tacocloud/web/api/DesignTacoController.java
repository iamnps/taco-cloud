package com.nps.tacocloud.web.api;



import com.nps.tacocloud.data.IngredientRepository;
import com.nps.tacocloud.data.OrderRepository;
import com.nps.tacocloud.data.TacoRepository;
import com.nps.tacocloud.data.UserRepository;
import com.nps.tacocloud.domain.Ingredient;
import com.nps.tacocloud.domain.Order;
import com.nps.tacocloud.domain.Taco;
import com.nps.tacocloud.domain.TacoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by peishen.nie on 2020/7/13.
 */

@RestController
@RequestMapping(value = "/design")
@SessionAttributes("order")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoARepository,
            OrderRepository orderRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoARepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<Ingredient> ingredients = new ArrayList<>();
        Iterator<Ingredient> iterator = ingredientRepository.findAll().iterator();
        while (iterator.hasNext()) {
            ingredients.add(iterator.next());
        }

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        String username = principal.getName();
        TacoUser user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        logger.error("errors:" + errors);
        /*
         * if(errors.hasErrors()){
         * return "design";
         * }
         */
        logger.info("Processing design:" + design.getName());
        Taco taco = tacoRepository.save(design);
        order.addDesign(taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(1, 12, Sort.by("creatAt").descending());
        // return tacoARepository.findAll(page);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optional = tacoRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Taco(), HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}
