package com.codeup.testrepo.services;

import com.codeup.testrepo.models.User;
import com.codeup.testrepo.models.UserWithRoles;
import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final ListingRepository listingRepository;

    public UserDetailsLoader(UserRepository userRepository ,RolesRepository rolesRepository, ListingRepository listingRepository) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        return new UserWithRoles(user);
    }
//    @Override
//    public UserDetails loadUserByListing(Long list) throws UsernameNotFoundException {
//        User user = users.fi(list);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found for " + username);
//        }
//        return new UserWithRoles(user);
//    }

}


