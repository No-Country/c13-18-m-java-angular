package com.grupo18.nocountry.greenpoint.recyclingPoint;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IRecyclingPointService {

    void createRecyclingPoint (RecyclingPoint recyclingPoint);

    Optional<RecyclingPoint> searchRecyclingPoint(Long id);

    void modifyRecyclingPoint(RecyclingPoint recyclingPoint);

    void deleteRecyclingPoint(Long id);

    List<RecyclingPoint> searchAll();

    List<RecyclingPoint> filterByTime(LocalTime openingTime, LocalTime closingTime);

    List<RecyclingPoint> filterByComuna(String comuna);

}
