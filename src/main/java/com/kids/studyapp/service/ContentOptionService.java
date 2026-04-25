package com.kids.studyapp.service;

import com.kids.studyapp.entity.ContentOption;
import com.kids.studyapp.repository.ContentOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContentOptionService {

    private final ContentOptionRepository repo;

    public ContentOptionService(ContentOptionRepository repo) {
        this.repo = repo;
    }

    public List<ContentOption> findAll() {
        return repo.findAllByOrderByIdAsc();
    }

    public void save(ContentOption option) {
        repo.save(option);
    }

    public Optional<ContentOption> findById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
