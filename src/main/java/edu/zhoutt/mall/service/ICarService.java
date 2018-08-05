package edu.zhoutt.mall.service;

import edu.zhoutt.mall.pojo.Car;

import java.util.List;

public interface ICarService {

    Car add(Long userId, Long productId, Integer total);

    Long deleteById(Long id);

    Long update(Long id, Integer total);

    List<Car> getList(Long userId);
}
