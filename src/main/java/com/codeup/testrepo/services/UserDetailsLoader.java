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
    private final UserRepository users;
    private final RolesRepository roles;
    private final ListingRepository list;

    public UserDetailsLoader(UserRepository users,RolesRepository roles,ListingRepository list) {
        this.users = users;
        this.list = list;
        this.roles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
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


