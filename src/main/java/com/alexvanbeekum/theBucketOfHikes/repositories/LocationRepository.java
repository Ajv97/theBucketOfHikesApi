package com.alexvanbeekum.theBucketOfHikes.repositories;

import com.alexvanbeekum.theBucketOfHikes.models.ListLocation;
import com.alexvanbeekum.theBucketOfHikes.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByName(String name);
    @Query("select distinct country from Location order by country")
    ArrayList<String> selectAllCountries();
    @Query("select distinct state from Location where country = ?1 order by state")
    ArrayList<String> selectStatesByCountry(String country);
    @Query("select id,name from Location where country = ?1 and state is null order by name")
    ArrayList<List<String>> selectAllLocationsByCountryAndNullState(String country);
    @Query("select id,name from Location where state = ?1 and country = ?2 order by name")
    ArrayList<List<String>> selectAllLocationsByStateAndCountry(String state, String country);
}
