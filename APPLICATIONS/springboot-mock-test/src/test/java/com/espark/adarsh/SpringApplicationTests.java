package com.espark.adarsh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.espark.adarsh.repository.DataRepository;
import com.espark.adarsh.repository.DataRepositoryImpl;
import com.espark.adarsh.service.DataService;
import com.espark.adarsh.service.DataServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringApplicationTests {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    private DataService dataService;

    @Test
    public void testForOneToFive()  {
        Integer range = 5;
        List<Integer> dataList = IntStream.range(0, range)
                .boxed()
                .collect(Collectors.toList());
        when(dataRepository.retrieveAllData(range)).thenReturn(dataList);
        assertEquals(dataList, dataService.retrieveAllData(range));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForException(){
        Integer range = 0;
            dataRepository.retrieveAllData(range);
    }


    @Test
    public void testForEven()  {
        Integer range = 5;
        List<Integer> evenList = IntStream.range(0, range)
                .boxed()
                .filter(element -> element % 2 == 0)
                .collect(Collectors.toList());

        when(dataRepository.retrieveAllData(range)).thenReturn(evenList);
        assertEquals(evenList, dataService.retrieveAllData(range));
    }


    @Test
    public void testForOdd()  {
        Integer range = 5;
        List<Integer> oddList = IntStream.range(0, range)
                .boxed()
                .filter(element -> element % 2 != 0)
                .collect(Collectors.toList());

        when(dataRepository.retrieveAllData(range)).thenReturn(oddList);
        assertEquals(oddList, dataService.retrieveAllData(range));
    }

}

