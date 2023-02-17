package com.codeup.testrepo.repositories;
import com.codeup.testrepo.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Categories, Long> {

    Categories findByName(String name);
    Categories findByUserIdAndName(Long id, String name );

}
