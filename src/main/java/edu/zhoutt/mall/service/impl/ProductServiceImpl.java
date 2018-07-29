package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.common.IsDown;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.dao.ICategoryMapper;
import edu.zhoutt.mall.dao.IProductMapper;
import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product add(String name, String image, BigDecimal price, String description, Long total, Long categoryId) {

        Date currTime = new Date();

        Product product = new Product();
        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setIsDown(IsDown.NORMAL.getCode());
        product.setTotal(total);
        product.setSell(0L);
        product.setCreateTime(currTime);
        product.setUpdateTime(currTime);

        productMapper.save(product);

        return product;
    }

    @Override
    public Long update(Long id, String name, String image, BigDecimal price, String description, Long total, Long categoryId) {

        Product product = productMapper.findById(id);
        Assert.notNull(product, "商品不存在");

        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setTotal(total);
        product.setSell(0L);
        product.setUpdateTime(new Date());

        return productMapper.update(product);
    }


    @Override
    public Long changeIsDown(Long id, IsDown isDown) {

        Product product = productMapper.findById(id);
        Assert.notNull(product, "商品不存在");

        product.setIsDown(isDown.getCode());
        product.setUpdateTime(new Date());

        return productMapper.update(product);
    }

    @Override
    public Long delete(Long id) {

        return productMapper.deleteById(id);
    }

    @Override
    public Product getSingle(Long id) {

        return productMapper.findById(id);
    }

    @Override
    public Page<Product> getPageByCategory(Long categoryId, Pageable pageable) {

        Category category = categoryMapper.findById(categoryId);
        Assert.notNull(category, "分类信息不正确");

        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(categoryId);
        findSubCategoryId(categoryIds, categoryId);

        return productMapper.findPageByCategory(categoryIds, pageable);
    }


    /**
     * 查询指定分类下的所有子分类Id
     */
    private void findSubCategoryId(List<Long> categoryIds, Long parentId) {

        List<Category> categories = categoryMapper.findByParentId(parentId);
        for (Category category : categories) {
            Long id = category.getId();
            categoryIds.add(id);
            findSubCategoryId(categoryIds, id);
        }
    }

}