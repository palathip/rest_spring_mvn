package com.rest_spring_mvn.repository;

import com.rest_spring_mvn.entity.ApplicationHd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationHdRepository extends JpaRepository<ApplicationHd, Long> {
    List<ApplicationHd> findTop10By();

    List<ApplicationHd> findTop20By();
}
