package com.rest_spring_mvn.repository;

import com.rest_spring_mvn.entity.ApplicationToken;
import com.rest_spring_mvn.model.ApplicationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationTokenRepository extends JpaRepository<ApplicationToken, Long> {
    List<ApplicationToken> findByUserNameEquals(String username);
    List<ApplicationToken> findByPassWordEquals(String username);
}
