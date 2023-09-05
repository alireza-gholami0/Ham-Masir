package com.example.foodapi.domain.log;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseLogger {
    Logger logger = LoggerFactory.getLogger(DatabaseLogger.class);


    @PrePersist
    public void NewEntityAttempt(Object entity) {
        logger.info("Try adding Entity at :" + entity.getClass().getName());
    }

    @PostPersist
    public void NewEntityAdded(Object entity) {
        logger.info("Added : " + entity.toString());
    }

    @PreRemove
    public void EntityRemovalAttempt(Object entity) {
        logger.info("Try removing Entity at :" + entity.getClass().getName());
    }

    @PostRemove
    public void EntityRemoval(Object entity) {
        logger.info("Removed : " + entity.toString());
    }

    @PreUpdate
    public void EntityUpdateAttempt(Object entity) {
        logger.info("Try updating Entity :" + entity.getClass().getName());
    }

    @PostUpdate
    public void EntityUpdate(Object entity) {
        logger.info("Updated : " + entity.toString());
    }
}
