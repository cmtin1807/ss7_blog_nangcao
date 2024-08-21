package com.example.app_blog.controller;


import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.service.blog.IBlogService;
import com.example.app_blog.service.category.ICategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final IBlogService blogService;


    private final ICategoryService categoryService;

    public CategoryController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView listCategory() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create new category successfully");
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView;
            modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        } else return new ModelAndView("/error_404");
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category,
                         RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Update category successfully");
        return "redirect:/categories";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("error_404");
        }

        Iterable<Blog> blogs = blogService.findAllByCategory(categoryOptional.get());

        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
