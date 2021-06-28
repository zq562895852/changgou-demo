package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     */
    List<Brand> findAll();
    /**
     * 根据id查询
     */
    Brand findBrandById(Integer id);
    /**
     *  新增
     */
    void add(Brand brand);

    /**
     * 根据id删除
     * @param id
     */
    void deleteBrandById(Integer id);

    /**
     * 更新brand
     * @param brand
     */
    void updateBrandById(Brand brand);

    /**
     * 多条件搜索
     * @param brand
     * @return
     */
    List<Brand> findBrandList(Brand brand);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findBrandListByPage(Integer page, Integer size);

    PageInfo<Brand> findBrandListByPageAndSearch(Brand brand, Integer page, Integer size);
}
