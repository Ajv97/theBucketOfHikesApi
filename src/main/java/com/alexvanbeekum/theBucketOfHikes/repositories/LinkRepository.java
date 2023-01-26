package com.alexvanbeekum.theBucketOfHikes.repositories;

import com.alexvanbeekum.theBucketOfHikes.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    boolean existsByLink(String link);
    ArrayList<Link> getAllByLocationId(Long locationId);
}
