package com.espark.adarsh.services;

import com.espark.adarsh.entity.Answer;
import com.espark.adarsh.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

import javax.validation.Valid;

public interface QuestionAnswerManager {

    Answer addAnswer(Long questionId, @Valid Answer answer);

    List<Answer> getAnswersByQuestionId(Long questionId);

    Answer updateAnswer(Long questionId, Long answerId,
            @Valid Answer answerRequest);

    ResponseEntity<?> deleteAnswer(Long questionId, Long answerId);

    Page<Question> getQuestions(Pageable pageable);

    Question createQuestion(@Valid Question question);

    boolean questionExistsById(Long questionId);

    Question updateQuestion(Long questionId, Question questionRequest);

    ResponseEntity<?> deleteQuestion(Long questionId);
}
