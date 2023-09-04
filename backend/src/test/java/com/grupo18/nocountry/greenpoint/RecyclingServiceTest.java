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
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecyclingServiceTest {

    @Mock
    private RecyclingPointRepository recyclingPointRepository;

    @InjectMocks
    private RecyclingPointService recyclingService;
    private List<RecyclingPoint> testRecyclingPoints;

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
    public void testFilterRecyclingPointsByComuna() {
        String comunaToFilter = "2";

        RecyclingPoint recyclingPoint2 = new RecyclingPoint();
        recyclingPoint2.setId(3L);
        recyclingPoint2.setComuna("1");
        recyclingPoint2.setAddress("Pedro 123");
        recyclingPoint2.setOpeningTime(LocalTime.parse("21:00"));
        recyclingPoint2.setClosingTime(LocalTime.parse("18:00"));

        RecyclingPoint recyclingPoint3 = new RecyclingPoint();
        recyclingPoint3.setId(4L);
        recyclingPoint3.setComuna("2");
        recyclingPoint3.setAddress("Santo 33");
        recyclingPoint3.setOpeningTime(LocalTime.parse("08:00"));
        recyclingPoint3.setClosingTime(LocalTime.parse("23:00"));

        RecyclingPoint recyclingPoint4 = new RecyclingPoint();
        recyclingPoint4.setId(5L);
        recyclingPoint2.setComuna("2");
        recyclingPoint4.setAddress("Juancito 23");
        recyclingPoint4.setOpeningTime(LocalTime.parse("06:00"));
        recyclingPoint4.setClosingTime(LocalTime.parse("12:00"));

        RecyclingPoint recyclingPoint5 = new RecyclingPoint();
        recyclingPoint5.setId(6L);
        recyclingPoint2.setComuna("3");
        recyclingPoint5.setAddress("Julian 48943");
        recyclingPoint5.setOpeningTime(LocalTime.parse("12:00"));
        recyclingPoint5.setClosingTime(LocalTime.parse("14:00"));

        testRecyclingPoints = new ArrayList<>();
        testRecyclingPoints.add(recyclingPoint2);
        testRecyclingPoints.add(recyclingPoint3);
        testRecyclingPoints.add(recyclingPoint4);
        testRecyclingPoints.add(recyclingPoint5);

        List<RecyclingPoint> expectedFilteredRecyclingPoints = new ArrayList<>();
        expectedFilteredRecyclingPoints.add(recyclingPoint3);
        expectedFilteredRecyclingPoints.add(recyclingPoint4);

        when(recyclingPointRepository.findByComuna(comunaToFilter))
                .thenReturn(expectedFilteredRecyclingPoints);

        List<RecyclingPoint> filteredRecyclingPoints = recyclingService.filterByComuna(comunaToFilter);

        assertEquals(expectedFilteredRecyclingPoints.size(), filteredRecyclingPoints.size());
        assertEquals(expectedFilteredRecyclingPoints, filteredRecyclingPoints);
    }

    @Test
    public void testDeleteRecyclingPoint() {
        recyclingService.deleteRecyclingPoint(1L);

        verify(recyclingPointRepository, times(1)).deleteById(1L);

        Optional<RecyclingPoint> deletedRecyclingPoint = recyclingService.searchRecyclingPoint(1L);
        assertFalse(deletedRecyclingPoint.isPresent());
    }
}