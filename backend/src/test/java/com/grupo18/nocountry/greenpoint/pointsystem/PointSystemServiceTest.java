package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import com.grupo18.nocountry.greenpoint.recyclable.RecyclableRepository;
import com.grupo18.nocountry.greenpoint.recyclable.RecyclableType;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PointSystemServiceTest {
    @InjectMocks
    private PointSystemService pointSystemService;
    @Mock
    private  RecyclableDetailsRepository detailsRepository;
    @Mock
    private  RecyclingTransactionRepository transactionRepository;
    @Mock
    private  RecyclableRepository recyclableRepository;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  ModelMapper mapper;


    @Test
    void calculatePoints(){
        List<Recyclable> recyclables = new ArrayList<>();
        Recyclable metal = new Recyclable(RecyclableType.METAL);
        recyclables.add(metal);
        recyclables.add(metal);
        recyclables.add(metal);
        recyclables.add(metal);

        int expected = metal.getPoints() * 4;
        assertEquals(expected,pointSystemService.getTotalPoints(recyclables));
    }



}