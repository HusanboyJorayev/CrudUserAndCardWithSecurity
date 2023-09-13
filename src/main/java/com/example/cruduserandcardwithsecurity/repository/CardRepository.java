package com.example.cruduserandcardwithsecurity.repository;


import com.example.cruduserandcardwithsecurity.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card>findByIdAndDeletedAtIsNull(Integer id);
    @Query(value = "select * from cards",
    nativeQuery = true)
    List<Card>getAllCard();
}
