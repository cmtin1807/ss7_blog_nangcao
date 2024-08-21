package com.example.app_blog.repository;

import com.example.app_blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
