package com.grupo18.nocountry.greenpoint.recyclingPoint;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingPointService implements IRecyclingPointService {

    private static final Logger logger = LogManager.getLogger(RecyclingPointService.class);

    @Autowired
    private RecyclingPointRepository recyclingPointRepository;

    @Override
    public void createRecyclingPoint(RecyclingPoint recyclingPoint) {
        logger.info("Entering createRecyclingPoint()");
        try {
            recyclingPointRepository.save(recyclingPoint);
            logger.info("Recycling point created");
        } catch (Exception e) {
            logger.error("Error while creating recycling point: {}" + e.getMessage(), e);
        }
        logger.info("Exiting createRecyclingPoint()");
    }

    @Override
    public Optional<RecyclingPoint> searchRecyclingPoint(Long id) {
        logger.info("Entering searchRecyclingPoint()");
        Optional<RecyclingPoint> recyclingPoint = recyclingPointRepository.findById(id);
        logger.info("Exiting searchRecyclingPoint()");
        return recyclingPoint;
    }

    @Override
    public void modifyRecyclingPoint(RecyclingPoint recyclingPoint) {
        logger.info("Entering modifyRecyclingPoint()");
        try {
            recyclingPointRepository.save(recyclingPoint);
            logger.info("Recycling point modified");
        } catch (Exception e) {
            logger.error("Error while modifying recycling point: {}" + e.getMessage(), e);
        }
        logger.info("Exiting modifyRecyclingPoin()");
    }

    @Override
    public void deleteRecyclingPoint(Long id) {
        logger.info("Entering deleteRecyclingPoint()");
        try {
            recyclingPointRepository.deleteById(id);
            logger.info("Recycling point deleted");
        } catch (Exception e) {
            logger.error("Error while deleting recycling point: {}" + e.getMessage(), e);
        }
        logger.info("Exiting deleteRecyclingPoint()");
    }

    @Override
    public List<RecyclingPoint> searchAll() {
        logger.info("Entering searchAll()");
        List<RecyclingPoint> recyclingPoints = recyclingPointRepository.findAll();
        logger.info("Exiting searchAll()");
        return recyclingPoints;
    }
}