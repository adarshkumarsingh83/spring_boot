package com.espark.adarsh.services;

import com.espark.adarsh.entity.Answer;
import org.springframework.http.ResponseEntity;

import java.util.List;

import javax.validation.Valid;

public interface AnswerService {

    List<Answer> getAnswersByQuestionId(Long questionId);

    Answer saveAnswer(@Valid Answer answerRequest);

    Answer updateAnswer(
            Long questionId,
            Long answerId,
            @Valid Answer answerRequest);

    ResponseEntity<?> deleteAnswer(
            Long questionId, Long answerId);
}
