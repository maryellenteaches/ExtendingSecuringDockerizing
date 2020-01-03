package com.example.ec.repo;

import com.example.ec.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

/**
 * Tour Package Repository Interface
 *
 * Created by Mary Ellen Bowman
 */
@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    /**
     * Lookup a tour package by the name.
     *
     * @param name name of the tour.
     * @return TourPackage if found, null otherwise.
     */
    Optional<TourPackage> findByName(@Param("name") String name);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends TourPackage> S save(S s);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(TourPackage tourPackage);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TourPackage> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll();
}

