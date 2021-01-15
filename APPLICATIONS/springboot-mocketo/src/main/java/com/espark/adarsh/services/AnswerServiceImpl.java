package com.espark.adarsh.services;

import com.espark.adarsh.entity.Answer;
import com.espark.adarsh.exception.ResourceNotFoundException;
import com.espark.adarsh.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public Answer saveAnswer(@Valid Answer answerRequest) {
        return this.answerRepository.save(answerRequest);
    }

    @Override
    public Answer updateAnswer(
            Long questionId, Long answerId,
            @Valid Answer answerRequest) {

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

    @Override
    public ResponseEntity<?> deleteAnswer(
            Long questionId, Long answerId) {

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

    }

    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
}
