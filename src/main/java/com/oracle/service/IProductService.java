package com.oracle.service;

import com.oracle.domain.Product;

import java.util.List;

public interface IProductService {
    public  List<Product> findAll(int page, int size) throws  Exception;

    void save(Product product) throws Exception;


    void delete(String[] productIds);

    List<Product> search(String productNum);

    List<Product> findCar(int page, int size);

    List<Product> findById(String productId);

    void updateById(String productId, Product product);

    void saveCost(Product product);

    void expurgate(String productId);

    List<Product> findAllCar();

    String findCost();

    List<Product> search1(String productNum);

    List<Product> findAllCar1();
}
