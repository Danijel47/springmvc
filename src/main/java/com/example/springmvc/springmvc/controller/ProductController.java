package com.example.springmvc.springmvc.controller;

import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    //@RequestMapping(path = "/products/add", method = RequestMethod.GET)
    @GetMapping("/products/add")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }

    //@RequestMapping(path = "products", method = RequestMethod.POST)
    @PostMapping("products")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    //@RequestMapping(path = "/products", method = RequestMethod.GET)
    @GetMapping("products")
    public String getAllProducts(Model model) {
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }

    //@RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    @GetMapping("/products/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") String id){
        model.addAttribute("product", productRepository.findOne(id));
        return "edit";
    }

    //@RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") String id){
        productRepository.delete(id);
        return "redirect:/products";
    }
}
