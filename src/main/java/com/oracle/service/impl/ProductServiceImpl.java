package com.oracle.service.impl;

import com.github.pagehelper.PageHelper;
import com.oracle.dao.IProductDao;
import com.oracle.domain.Product;
import com.oracle.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    public List<Product> findAll(int page, int size) throws Exception {
        PageHelper .startPage(page, size);
        List<Product> productList = productDao.findAll();
        String cost = productDao.findCost();
        Product product = new Product();
        for(int i = 0 ; i<productList.size();i++){
            product=productList.get(i);
            product.setCost(cost);
        }

/*        productList.forEach(x -> {
            if(x.getProductStatus() == 0) {
                x.setProductStatusStr("曹尼玛");
            }else{
                x.setProductStatusStr("asdf");
            }
        });*/
        return productList;
    }


    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(String[] productIds) {
        for(String productId:productIds) {
            productDao.delete(productId);

        }
    }

    @Override
    public List<Product> search(String productNum) {
        return productDao.search(productNum);
    }

    @Override
    public List<Product> search1(String productNum) {
        return productDao.search1(productNum);
    }

    @Override
    public List<Product> findCar(int page, int size) {
        PageHelper .startPage(page, size);
        List<Product> productList = productDao.findCar();
        String cost = productDao.findCost();
        Product product = new Product();
        for(int i = 0 ; i<productList.size();i++){
            product=productList.get(i);
            product.setCost(cost);
        }
        return productList;
    }

    @Override
    public List<Product> findById(String productId) {
        return productDao.findById(productId);
    }

    @Override
    public void updateById(String productId, Product product) {
        productDao.updateById(productId,product);
    }

    @Override
    public void saveCost(Product product) {
        productDao.saveCost(product);
    }

    @Override
    public void expurgate(String productId) {
        productDao.expurgate(productId);
    }

    @Override
    public List<Product> findAllCar() {
        return productDao.findAllCar();
    }

    @Override
    public List<Product> findAllCar1() {
        return productDao.findAll1();
    }

    @Override
    public String findCost() {
        return productDao.findCost();
    }

}
