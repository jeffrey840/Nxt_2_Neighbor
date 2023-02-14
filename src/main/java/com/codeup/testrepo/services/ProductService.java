
// work in progress, this will filter by search
package com.codeup.testrepo.services;

import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ListingRepository repo;

    public List<Listings> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public interface ListingService{
        Collection<Listings> getListing();
        Listings addListing(Listings listings);
    }
}
