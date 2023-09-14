package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.RecyclableRepository;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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





}