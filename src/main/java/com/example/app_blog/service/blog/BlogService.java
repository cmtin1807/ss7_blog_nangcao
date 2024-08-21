package com.example.app_blog.service.blog;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    private final IBlogRepository blogRepository;
    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("createdAt").descending());
        return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(Pageable pageable, String title) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("createdAt").descending());
        return blogRepository.findAllByTitleContaining(pageable, title);
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }
}
