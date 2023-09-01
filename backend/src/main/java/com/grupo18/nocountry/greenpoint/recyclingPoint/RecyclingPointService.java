package com.grupo18.nocountry.greenpoint.recyclingPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecyclingPointService implements IRecyclingPointService {

    private static final Logger logger = LogManager.getLogger(RecyclingPointService.class);


    @Autowired
    private RecyclingPointRepository recyclingPointRepository;

    @Override
    public void createRecyclingPoint(RecyclingPoint recyclingPoint) {
        logger.trace("Entering createRecyclingPoint()");
        try {
            recyclingPointRepository.save(recyclingPoint);
            logger.info("Recycling point created");
        } catch (Exception e) {
            logger.error("Error while creating recycling point: {}", e);
            throw new RuntimeException("Failed to create recycling point", e);
        }
        logger.trace("Exiting createRecyclingPoint()");

    }

    @Override
    public Optional<RecyclingPoint> searchRecyclingPoint(Long id) {
        logger.info("Entering searchRecyclingPoint()");
        try {
            Optional<RecyclingPoint> recyclingPoint = recyclingPointRepository.findById(id);
            logger.info("Exiting searchRecyclingPoint()");
            return recyclingPoint;
        } catch (Exception e) {
            logger.error("Error while searching for recycling point: {}", e);
            throw new RuntimeException("Failed to search for recycling point", e);
        }
    }

    @Override
    public void modifyRecyclingPoint(RecyclingPoint recyclingPoint) {
        logger.info("Entering modifyRecyclingPoint()");
        try {
            recyclingPointRepository.save(recyclingPoint);
            logger.info("Recycling point modified");
        } catch (Exception e) {
            logger.error("Error while modifying recycling point: {}", e);
            throw new RuntimeException("Failed to modify recycling point", e);
        }
        logger.info("Exiting modifyRecyclingPoint()");
    }

    @Override
    public void deleteRecyclingPoint(Long id) {
        logger.info("Entering deleteRecyclingPoint()");
        try {
            recyclingPointRepository.deleteById(id);
            logger.info("Recycling point deleted");
        } catch (Exception e) {
            logger.error("Error while deleting recycling point: {}", e);
            throw new RuntimeException("Failed to delete recycling point", e);
        }
        logger.info("Exiting deleteRecyclingPoint()");
    }

    @Override
    public List<RecyclingPoint> searchAll() {
        logger.info("Entering searchAll()");
        try {
            List<RecyclingPoint> recyclingPoints = recyclingPointRepository.findAll();
            logger.info("Exiting searchAll()");
            return recyclingPoints;
        } catch (Exception e) {
            logger.error("Error while searching for all recycling points: {}", e);
            throw new RuntimeException("Failed to retrieve recycling points", e);
        }
    }

    @Override
    public List<RecyclingPoint> filterByTime(LocalTime openingTime, LocalTime closingTime) {
        logger.info("Entering filterByTime() with openingTime: {} and closingTime: {}", openingTime, closingTime);
        try {
            List<RecyclingPoint> filteredRecyclingPoints = recyclingPointRepository.findByOpeningAndClosingTime(openingTime, closingTime);
            logger.info("Exiting filterByTime() with {} filtered recycling points", filteredRecyclingPoints.size());
            return filteredRecyclingPoints;
        } catch (Exception e) {
            logger.error("Error while filtering recycling points by time: {}", e);
            throw new RuntimeException("Failed to filter recycling points by time", e);
        }
    }
}