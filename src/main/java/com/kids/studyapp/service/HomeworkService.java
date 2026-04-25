package com.kids.studyapp.service;

import com.kids.studyapp.entity.Homework;
import com.kids.studyapp.repository.HomeworkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> findAll() {
        return homeworkRepository.findAllByOrderByDueDateAsc();
    }

    public void save(Homework homework) {
        homeworkRepository.save(homework);
    }

    public void toggleDone(Long id) {
        homeworkRepository.findById(id).ifPresent(hw -> {
            hw.setDone(!hw.isDone());
            homeworkRepository.save(hw);
        });
    }

    public void delete(Long id) {
        homeworkRepository.deleteById(id);
    }
}
