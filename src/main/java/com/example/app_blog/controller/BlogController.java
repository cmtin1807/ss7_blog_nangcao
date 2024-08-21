package com.example.app_blog.controller;


import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.service.blog.IBlogService;
import com.example.app_blog.service.category.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;


    private final ICategoryService categoryService;

    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }


    @ModelAttribute("categories")
    public Iterable<Category> ListCategory() {
        return categoryService.findAll();
    }


    @GetMapping
    public ModelAndView findAllBlogs(@PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView listTittleSearch(@RequestParam("search") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs;
        String searchValue = search.orElse("");
        if(!searchValue.isEmpty()){
            blogs = blogService.findAllByTitleContaining(pageable,searchValue);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("search", searchValue);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("blog") Blog blog,
                         RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Create new blog successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/update");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("blog") Blog blog,
                         RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Update blog successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        blogService.remove(id);
        redirect.addFlashAttribute("message", "Delete blog successfully");
        return "redirect:/blogs";
    }
}