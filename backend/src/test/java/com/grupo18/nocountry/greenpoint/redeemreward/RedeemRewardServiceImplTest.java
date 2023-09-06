package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.reward.Reward;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RedeemRewardServiceImplTest {

    @InjectMocks
    private RedeemRewardServiceImpl service;

    @Test
    void getRandomCode() {
        Reward reward = new Reward(1l, "Patin", 1200, "desc", "", null);
        Reward reward2 = new Reward(1l, "Bicileta", 1200, "desc", "", null);
        Reward reward3 = new Reward(1l, "Descuento 50% en Instrumentos", 1200, "desc", "", null);

        String code = service.voucherCode(reward);
        String code2 = service.voucherCode(reward2);
        String code3 = service.voucherCode(reward3);

        log.info(code);
        log.info(code2);
        log.info(code3);

    }

}