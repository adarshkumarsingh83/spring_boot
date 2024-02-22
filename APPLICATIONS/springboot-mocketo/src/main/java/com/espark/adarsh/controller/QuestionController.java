package com.espark.adarsh.controller;

import com.espark.adarsh.entity.Question;
import com.espark.adarsh.exception.ResourceNotFoundException;
import com.espark.adarsh.repository.QuestionRepository;
import com.espark.adarsh.services.QuestionAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuestionController {


    @Autowired
    private QuestionAnswerManager questionAnswerManager;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<Question> getQuestions(Pageable pageable) {
        return questionAnswerManager.getQuestions(pageable);
    }


    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question question) {
        return questionAnswerManager.createQuestion(question);
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(
            @PathVariable Long questionId,
            @Valid @RequestBody Question questionRequest) {
        return questionAnswerManager.updateQuestion(questionId, questionRequest);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionAnswerManager.deleteQuestion(questionId);
    }
}
