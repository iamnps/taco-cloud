package com.nps.tacocloud.web.api;



import com.nps.tacocloud.data.IngredientRepository;
import com.nps.tacocloud.data.OrderRepository;
import com.nps.tacocloud.data.TacoRepository;
import com.nps.tacocloud.data.TacoUserRepository;
import com.nps.tacocloud.domain.Ingredient;
import com.nps.tacocloud.domain.Order;
import com.nps.tacocloud.domain.Taco;
import com.nps.tacocloud.domain.TacoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
@RequestMapping(value = "/design", produces = "application/json")
@SessionAttributes("order")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    private OrderRepository orderRepository;

    private TacoUserRepository tacoUserRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoARepository,
                                OrderRepository orderRepository, TacoUserRepository tacoUserRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoARepository;
        this.orderRepository = orderRepository;
        this.tacoUserRepository = tacoUserRepository;
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
        TacoUser user = tacoUserRepository.findByUsername(username);
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
    public Resources<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(1, 12, Sort.by("creatAt").descending());
        List<Taco> list = tacoRepository.findAll(page).getContent();
        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(list);
        Resources<TacoResource> resources = new Resources<TacoResource>(tacoResources);
//         resources.add(new Link("http://localhost:8080/design/recent", "recents")); 硬编码
//        resources.add(ControllerLinkBuilder.linkTo(DesignTacoController.class).slash("recent").withRel("recents")); 还可以再优化
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
         return resources;
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
    @ResponseStatus(HttpStatus.CREATED) //201表示新增成功
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable Long orderId, @RequestBody Order patch){
        Order order = orderRepository.findById(orderId).get();
        if(null != patch.getZip()){
            order.setZip(patch.getZip());
        }
        if(null != patch.getCcCvv()){
            order.setCcCvv(patch.getCcCvv());
        }

        return orderRepository.save(order);
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e){}
    }
}
