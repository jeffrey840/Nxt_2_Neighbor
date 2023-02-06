package com.codeup.testrepo.repositories;

import com.codeup.testrepo.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository{
    Listing findByTitle(String title);

    Listing findByBody(String body);
}
