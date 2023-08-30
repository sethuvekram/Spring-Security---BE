package com.App.repository;

import com.App.model.Products;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    Optional<Products> findById(SingularAttribute<AbstractPersistable, Serializable> id);

    List<Products> findByCategory(String category);
}
