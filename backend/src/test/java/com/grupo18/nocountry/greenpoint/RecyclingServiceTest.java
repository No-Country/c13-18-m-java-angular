package com.grupo18.nocountry.greenpoint;

import com.grupo18.nocountry.greenpoint.recyclingPoint.RecyclingPoint;
import com.grupo18.nocountry.greenpoint.recyclingPoint.RecyclingPointRepository;
import com.grupo18.nocountry.greenpoint.recyclingPoint.RecyclingPointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RecyclingServiceTest {

    @Mock
    private RecyclingPointRepository recyclingPointRepository;

    @InjectMocks
    private RecyclingPointService recyclingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRecyclingPoint() {
        RecyclingPoint recyclingPoint = new RecyclingPoint();
        recyclingPoint.setId(1L);
        recyclingPoint.setAddress("Sample Address");
        recyclingPoint.setOpeningTime(LocalTime.parse("08:00"));
        recyclingPoint.setClosingTime(LocalTime.parse("18:00"));


        recyclingService.createRecyclingPoint(recyclingPoint);

        verify(recyclingPointRepository, times(1)).save(recyclingPoint);
    }

    @Test
    public void testFilterRecyclingPointsByTime() {
        LocalTime openingTime = LocalTime.of(8, 0);
        LocalTime closingTime = LocalTime.of(14, 0);
        List<RecyclingPoint> filteredRecyclingPoints = new ArrayList<>();
        filteredRecyclingPoints.add(new RecyclingPoint());
        filteredRecyclingPoints.add(new RecyclingPoint());

        when(recyclingPointRepository.findByOpeningAndClosingTime(openingTime, closingTime))
                .thenReturn(filteredRecyclingPoints);

        List<RecyclingPoint> foundRecyclingPoints =
                recyclingService.filterByTime(openingTime, closingTime);

        assertEquals(2, foundRecyclingPoints.size());
    }
    @Test
    public void testDeleteRecyclingPoint() {
        recyclingService.deleteRecyclingPoint(1L);

        verify(recyclingPointRepository, times(1)).deleteById(1L);

        Optional<RecyclingPoint> deletedRecyclingPoint = recyclingService.searchRecyclingPoint(1L);
        assertFalse(deletedRecyclingPoint.isPresent());
    }
}