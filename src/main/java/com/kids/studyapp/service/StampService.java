package com.kids.studyapp.service;

import com.kids.studyapp.entity.Stamp;
import com.kids.studyapp.repository.StampRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StampService {

    private final StampRepository stampRepository;

    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

    public List<Stamp> findAll() {
        return stampRepository.findAllByOrderByEarnedDateDesc();
    }

    public long count() {
        return stampRepository.count();
    }
}
