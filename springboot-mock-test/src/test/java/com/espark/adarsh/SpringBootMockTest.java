package com.espark.adarsh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.espark.adarsh.repository.DataRepositoryImpl;
import com.espark.adarsh.service.DataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(MockitoJUnitRunner.class)
public class SpringBootMockTest {


    @Mock
    private DataRepositoryImpl dataRepository ;

    @InjectMocks
    private DataServiceImpl dataService;


    @Test
    public void testForOneToFive() {
        Integer range = 5;
        List<Integer> dataList = IntStream.range(0, range)
                .boxed()
                .collect(Collectors.toList());
        when(dataRepository.retrieveAllData(range)).thenReturn(dataList);
        assertEquals(dataList, dataService.retrieveAllData(range));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForException() {
        Mockito.when(this.dataRepository.retrieveAllData(0))
                .thenThrow(new IllegalArgumentException());
        dataRepository.retrieveAllData(0);
        verify(dataRepository, times(1))
                .retrieveAllData(0);
    }

    @Test
    public void testForEven() {
        Integer range = 5;
        List<Integer> evenList = IntStream.range(0, range)
                .boxed()
                .filter(element -> element % 2 == 0)
                .collect(Collectors.toList());

        when(dataRepository.retrieveAllData(range)).thenReturn(evenList);
        assertEquals(evenList, dataService.retrieveAllData(range));
    }


    @Test
    public void testForOdd() {
        Integer range = 5;
        List<Integer> oddList = IntStream.range(0, range)
                .boxed()
                .filter(element -> element % 2 != 0)
                .collect(Collectors.toList());

        when(dataRepository.retrieveAllData(range)).thenReturn(oddList);
        assertEquals(oddList, dataService.retrieveAllData(range));
    }
}
