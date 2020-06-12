package com.espark.adarsh.services;

import com.espark.adarsh.entity.Answer;
import com.espark.adarsh.entity.Question;
import com.espark.adarsh.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.validation.Valid;

@Service
public class QuestionAnswerManagerImpl implements QuestionAnswerManager {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;


    @Override
    public Answer addAnswer(Long questionId, @Valid Answer answer) {
        return this.questionService.findbyId(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerService.saveAnswer(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return this.answerService.getAnswersByQuestionId(questionId);
    }

    @Override
    public Answer updateAnswer(
            Long questionId, Long answerId, @Valid Answer answerRequest) {
        return this.answerService.updateAnswer(questionId, answerId, answerRequest);
    }

    @Override
    public ResponseEntity<?> deleteAnswer(Long questionId, Long answerId) {
        return this.answerService.deleteAnswer(questionId, answerId);
    }

    @Override
    public Page<Question> getQuestions(Pageable pageable) {
        return this.questionService.getQuestions(pageable);
    }

    @Override
    public Question createQuestion(@Valid Question question) {
        return this.questionService.createQuestion(question);
    }

    @Override
    public boolean questionExistsById(Long questionId) {
        return this.questionService.questionExistsById(questionId);
    }

    @Override
    public Question updateQuestion(Long questionId, Question questionRequest) {
        return this.questionService.updateQuestion(questionId, questionRequest);
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        return this.questionService.deleteQuestion(questionId);
    }


    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }
}
