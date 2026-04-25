package com.kids.studyapp.repository;

import com.kids.studyapp.entity.ContentOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContentOptionRepository extends JpaRepository<ContentOption, Long> {
    List<ContentOption> findAllByOrderByIdAsc();
}
