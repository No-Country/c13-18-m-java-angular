package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import com.grupo18.nocountry.greenpoint.recyclable.RecyclableRepository;
import com.grupo18.nocountry.greenpoint.recyclable.RecyclableType;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
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
class PointSystemServiceImplTest {
    @InjectMocks
    private PointSystemServiceImpl pointSystemService;
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
        Recyclable metal = new Recyclable();
        metal.setRecyclableType(RecyclableType.METAL);
        Recyclable vidrio = new Recyclable();
        vidrio.setRecyclableType(RecyclableType.VIDRIO);
        recyclables.add(metal);
        recyclables.add(metal);
        recyclables.add(vidrio);
        recyclables.add(vidrio);

        List<RecyclableRequest> recyclablesRequest = new ArrayList<>();
        RecyclableRequest r1 = new RecyclableRequest(metal,1200);
        RecyclableRequest r2 = new RecyclableRequest(vidrio,300);
          recyclablesRequest.add(r1);
          recyclablesRequest.add(r2);

        RecycleRequest recycleRequests = new RecycleRequest();
        recycleRequests.setRecyclables(recyclablesRequest);
        int expected = 6200;
        assertNotEquals(expected,pointSystemService.getTotalPoints(recyclablesRequest));
    }



}