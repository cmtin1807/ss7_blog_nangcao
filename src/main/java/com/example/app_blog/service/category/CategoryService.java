package com.example.app_blog.service.category;

import com.example.app_blog.model.Category;
import com.example.app_blog.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
