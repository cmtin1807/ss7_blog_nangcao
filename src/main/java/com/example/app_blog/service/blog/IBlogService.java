package com.example.app_blog.service.blog;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog> {
    public Page<Blog> findAll(Pageable pageable);

    public Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByTitleContaining(Pageable pageable, String title);
}
