package com.codeup.testrepo.repositories;


import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listings, Long>{
//    Listings findByListing(Long list );

    @Query("SELECT p FROM Listings p WHERE CONCAT(p.address, ' ', p.description, ' ', p.title, ' ', p.price) LIKE %?1%")
    public List<Listings> search(String keyword);

//        @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//            + " OR p.brand LIKE %?1%"
//            + " OR p.madein LIKE %?1%"
//            + " OR CONCAT(p.price, '') LIKE %?1%")
//    public List<Product> search(String keyword);





}
