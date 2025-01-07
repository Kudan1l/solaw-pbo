package com.SoLAW.repository;

import com.SoLAW.model.Articles;
import com.SoLAW.model.Documents;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long> {

}
