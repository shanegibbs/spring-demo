package com.gibbsdevops.springdemo.repo;

import com.gibbsdevops.springdemo.model.Thing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThingRepository extends CrudRepository<Thing, Long> {
    List<Thing> findByName(String name);
}
