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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

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


        recyclingService.createRecyclingPoint(recyclingPoint);

        verify(recyclingPointRepository, times(1)).save(recyclingPoint);
    }

    @Test
    public void testDeleteRecyclingPoint() {
        recyclingService.deleteRecyclingPoint(1L);

        verify(recyclingPointRepository, times(1)).deleteById(1L);

        Optional<RecyclingPoint> deletedRecyclingPoint = recyclingService.searchRecyclingPoint(1L);
        assertFalse(deletedRecyclingPoint.isPresent());
    }
}