package com.codeup.testrepo.repositories;


import com.codeup.testrepo.models.Listings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listings, Long>{
    Listings findByTitle(String title);

    Listings findByBody(String body);
}
