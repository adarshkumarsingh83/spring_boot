package com.espark.adarsh.services;

import com.espark.adarsh.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import javax.validation.Valid;

public interface QuestionService {

    Page<Question> getQuestions(Pageable pageable);

    Question createQuestion(@Valid Question question);

    boolean questionExistsById(Long questionId);

    Question updateQuestion(
            Long questionId, Question questionRequest);

    Optional<Question> findbyId(Long questionId);

    ResponseEntity<?> deleteQuestion(Long questionId);
}
