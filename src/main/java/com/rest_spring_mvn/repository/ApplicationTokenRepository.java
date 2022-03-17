package com.rest_spring_mvn.repository;

import com.rest_spring_mvn.entity.ApplicationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationTokenRepository extends JpaRepository<ApplicationToken, Long> {
    List<ApplicationToken> findTop10By();

    List<ApplicationToken> findTop20By();
}
