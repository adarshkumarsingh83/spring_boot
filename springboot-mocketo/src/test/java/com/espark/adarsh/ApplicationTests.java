package com.espark.adarsh;

import com.espark.adarsh.entity.Question;
import com.espark.adarsh.repository.AnswerRepository;
import com.espark.adarsh.repository.QuestionRepository;
import com.espark.adarsh.services.AnswerService;
import com.espark.adarsh.services.AnswerServiceImpl;
import com.espark.adarsh.services.QuestionAnswerManager;
import com.espark.adarsh.services.QuestionAnswerManagerImpl;
import com.espark.adarsh.services.QuestionService;
import com.espark.adarsh.services.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.Optional;

@lombok.extern.slf4j.Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {


    @InjectMocks
    private QuestionAnswerManagerImpl questionAnswerManager;

    @Mock
    private QuestionServiceImpl questionService;

    @Mock
    private AnswerServiceImpl answerService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;


    @Before
    public void init() {

        questionService.setQuestionRepository(questionRepository);
        answerService.setAnswerRepository(answerRepository);
        questionAnswerManager.setAnswerService(answerService);
        questionAnswerManager.setQuestionService(questionService);

    }

    @Test
    public void testQuestionCreation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Question question =
                mapper.readValue(new File("src/test/resources/json/question.json"), Question.class);
        Question questionResponse =
                mapper.readValue(new File("src/test/resources/json/question-response.json"), Question.class);
        //Mockito.when(questionRepository.save(question)).thenReturn(questionResponse);

        Question response = this.questionService.createQuestion(question);
        System.out.println(response);

    }

}
