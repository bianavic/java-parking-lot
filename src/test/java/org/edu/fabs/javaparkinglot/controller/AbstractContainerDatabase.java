package org.edu.fabs.javaparkinglot.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerDatabase {

    static PostgreSQLContainer POSTGRE_SQL_CONTAINER = null;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest");
        POSTGRE_SQL_CONTAINER.start();

        System.out.println("****************************** " + POSTGRE_SQL_CONTAINER.getJdbcUrl() + " ******************************");
        System.out.println("****************************** " + POSTGRE_SQL_CONTAINER.getDatabaseName() + " ******************************");

        System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
    }

}
