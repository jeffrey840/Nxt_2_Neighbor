
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
    private ListingRepository listDao;
public ProductService(ListingRepository listDao){
    this.listDao = listDao;
}
    public List<Listings> listAll(String keyword) {
        if (keyword != null) {
            return listDao.search(keyword);
        }
        return listDao.findAll();
    }
    public interface ListingService{
        Collection<Listings> getListing();
        Listings addListing(Listings listings);
    }
}
