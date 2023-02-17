package com.codeup.testrepo.repositories;

import com.codeup.testrepo.models.Interests;
import com.codeup.testrepo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestRepository extends JpaRepository<Interests, Long> {

}