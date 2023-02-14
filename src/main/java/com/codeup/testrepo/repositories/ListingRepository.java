package com.codeup.testrepo.repositories;


import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listings, Long>{
    @Query("SELECT p FROM Listings p WHERE CONCAT(p.address, ' ', p.description, ' ', p.title, ' ', p.price) LIKE %?1%")
    public List<Listings> search(String keyword);


    Listings findByTitle(String title);
    Listings findByAddress(String address);
    Listings findByDescription(String description);
    Listings findByPrice(Long price);

}
