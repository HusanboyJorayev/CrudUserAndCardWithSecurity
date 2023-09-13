package com.example.cruduserandcardwithsecurity.repository;


import com.example.cruduserandcardwithsecurity.model.AuthRefreshSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRefreshSessionRepository extends CrudRepository<AuthRefreshSession,String> {

}
