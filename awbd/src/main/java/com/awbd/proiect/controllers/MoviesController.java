package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Category;
import com.awbd.proiect.domain.Product;
import com.awbd.proiect.services.CategoryService;
import com.awbd.proiect.services.ImageService;
import com.awbd.proiect.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    /*    @RequestMapping("/product/list")
    public String productsList(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);

        return "products";
    }*/

    // SHOP REDIRECT AND CONTROLLER


    @RequestMapping("/shop")
    public ModelAndView shopList(){
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    // ABOUT REDIRECT AND CONTROLLER


    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String index() {
        return "about.html";
    }

    // FROM THE LAB BELOW


    @RequestMapping("/product/list")
    public ModelAndView productsList(){
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/product/info/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("product",
                productService.findById(Long.valueOf(id)));
        return "info";
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteById(@PathVariable String id){
        productService.deleteById(Long.valueOf(id));
        return "redirect:/product/list";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categoriesAll = categoryService.findAll();

        model.addAttribute("categoriesAll", categoriesAll );
        return "productform";
    }

    @PostMapping("/product")
    public String saveOrUpdate(@ModelAttribute Product product,
                               @RequestParam("imagefile") MultipartFile file){
        Product savedProduct = productService.save(product);
        imageService.saveImageFile(Long.valueOf(savedProduct.getId()), file);
        //return "redirect:/product/info/" + savedProduct.getId();
        return "redirect:/product/list" ;
    }

}