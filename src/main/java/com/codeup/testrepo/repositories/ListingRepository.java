package com.codeup.testrepo.repositories;


import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listings, Long>{
//    Listings findByListing(Long list );
}
