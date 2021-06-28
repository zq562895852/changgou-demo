package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    //注入通用Mapper
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Brand findBrandById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 根据id删除品牌
     * @param id
     */
    @Override
    public void deleteBrandById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新操作
     * @param brand
     */
    @Override
    public void updateBrandById(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 条件搜索
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findBrandList(Brand brand) {
        //条件搜索，使用Example构建一个搜索对象
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findBrandListByPage(Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        List<Brand> brands = brandMapper.selectAll();
        //brands 会给到PageInfo里面的list属性
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页 + 条件
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findBrandListByPageAndSearch(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        //条件搜索 通过Example
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);

        return new PageInfo<>(brands);
    }

    public Example createExample(Brand brand){
        //自定义条件搜索对象  Example传入一个类型字节码
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if(brand != null){
            if(!StringUtils.isEmpty(brand.getName())){
                //第一个参数是属性名称，第二个参数是拼接的字段  like "%name%" 所以要拼接一个%
                criteria.andLike("name", "%"+brand.getName()+"%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())){
                //andEqualTo相等的意思，会拼接一个  = 的sql
                criteria.andEqualTo("letter", brand.getLetter());
            }

        }
        return example;

    }
}
