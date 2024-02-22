package com.espark.adarsh.services;

import com.espark.adarsh.entity.Question;
import com.espark.adarsh.exception.ResourceNotFoundException;
import com.espark.adarsh.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import javax.validation.Valid;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question createQuestion(@Valid Question question) {
        return questionRepository.save(question);
    }

    @Override
    public boolean questionExistsById(Long questionId) {
        return questionRepository.existsById(questionId);
    }

    @Override
    public Question updateQuestion(
            Long questionId, Question questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @Override
    public Optional<Question> findbyId(Long questionId){
        return questionRepository.findById(questionId);
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
