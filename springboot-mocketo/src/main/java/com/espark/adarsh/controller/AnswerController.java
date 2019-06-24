package com.espark.adarsh.controller;

import com.espark.adarsh.entity.Answer;
import com.espark.adarsh.exception.ResourceNotFoundException;
import com.espark.adarsh.repository.AnswerRepository;
import com.espark.adarsh.repository.QuestionRepository;
import com.espark.adarsh.services.QuestionAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private QuestionAnswerManager questionAnswerManager;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return questionAnswerManager.getAnswersByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(
            @PathVariable Long questionId,
            @Valid @RequestBody Answer answer) {
        return this.questionAnswerManager.addAnswer(questionId, answer);
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(
            @PathVariable Long questionId,
            @PathVariable Long answerId,
            @Valid @RequestBody Answer answerRequest) {
        if (!questionAnswerManager.questionExistsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }
        return questionAnswerManager.updateAnswer(questionId, answerId, answerRequest);
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(
            @PathVariable Long questionId,
            @PathVariable Long answerId) {
        if (!questionAnswerManager.questionExistsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }
        return questionAnswerManager.deleteAnswer(questionId, answerId);

    }
}