package com.oracle.dao;

import com.oracle.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {

    @Select("select * from car where status ='0'")
    List<Product> findAll()throws Exception;

    @Insert("insert into car(carNum,name,inTime,carHouse,VIN,status，CITY，identityNumber) " +
            "values(#{carNum},#{name},#{inTime},#{carHouse},#{VIN},'0'，#{CITY},#{identityNumber})")
    void save(Product product);

    @Update("update car set outTime=sysdate,status='1' where  id=#{productId}")
    void delete(@Param("productId") String productId);

    @Select("select * from car where carNum like concat(concat('%',#{productNum}),'%') and status='0'")
    List<Product> search(String productNum);
    @Select("select * from car where status = '1'")
    List<Product> findCar();

    @Select("select * from car where id=#{productId}")
    List<Product> findById(String productId);
    @Update("update car set carNum=#{arg1.carNum},name=#{arg1.name},inTime=#{arg1.inTime},carHouse=#{arg1.carHouse},VIN=#{arg1.VIN},CITY=#{arg1.CITY},identityNumber=#{arg1.identityNumber}where  id=#{productId}")
    void updateById(@Param("productId") String productId, Product product);
    @Update("update cost set cost=#{cost}")
    void saveCost(Product product);

    @Select("select cost from cost")
    String findCost();

   // @Update("update car set status='2' where id =#{productId} ")
    @Delete("delete from car where id = #{productId}")
    void expurgate(@Param("productId") String productId);

    @Select("select * from car where status='0'")
    List<Product> findAllCar();

    @Select("select * from car where carNum like concat(concat('%',#{productNum}),'%') and status='1'")
    List<Product> search1(String productNum);
    @Select("select * from car where status='1'")
    List<Product> findAll1();
}
