package com.example.cruduserandcardwithsecurity.repository;


import com.example.cruduserandcardwithsecurity.model.AuthAccessSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthAccessSessionRepository extends CrudRepository<AuthAccessSession,String> {
}
