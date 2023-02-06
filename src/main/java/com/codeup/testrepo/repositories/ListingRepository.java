package com.codeup.testrepo.repositories;

import com.codeup.testrepo.models.Listing;
import com.codeup.testrepo.models.Listings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listings, Long>{
    Listing findByTitle(String title);

    Listing findByDescription(String description);
}
