package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IProductMapper {

    @Options(useGeneratedKeys = true, keyProperty = "product.id")
    @Insert("INSERT INTO product (name, image, price, description, is_down, total, sell, category_id, create_time, update_time) " +
            "VALUES (#{product.name}, #{product.image}, #{product.price}, #{product.description}, #{product.isDown}," +
            "#{product.total}, #{product.sell}, #{product.categoryId}, #{product.createTime}, #{product.updateTime})")
    void save(@Param("product") Product product);

    @Select("SELECT id, name, image, price, description, is_down, total, sell, category_id, create_time, update_time " +
            "FROM product WHERE id = #{id}")
    Product findById(Long id);

    @Update("UPDATE product SET name = #{product.name}, image = #{product.image}, price = #{product.price}, " +
            "description = #{product.description}, is_down = #{product.isDown}, total = #{product.total}, " +
            "sell = #{product.sell}, category_id = #{product.categoryId}, update_time = #{product.updateTime} " +
            "WHERE id = #{product.id}")
    long update(@Param("product") Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    long deleteById(Long id);

    Page<Product> findPageByCategory(@Param("categoryIds") List<Long> categoryIds, @Param("pageable") Pageable pageable);

    Page<Product> findPageBySellDesc(Pageable pageable);
}