package com.sistearth.backend.services;

public interface Service {
    void registerRoutes() throws ServiceException;
    void registerFilters() throws ServiceException;
}
