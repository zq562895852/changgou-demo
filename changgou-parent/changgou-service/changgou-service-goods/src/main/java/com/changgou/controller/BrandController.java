package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域设置
public class BrandController {
    //注入service
    @Autowired
    private BrandService brandService;
    /**
     * 查询所有品牌
     *
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return new Result<List<Brand>>(true, 200, "查询所有成功", brands);
    }

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findBrandById(@PathVariable(value = "id") Integer id){
        Brand brand =  brandService.findBrandById(id);
        return new Result<>(true, StatusCode.OK, "根据Id查询成功", brand);

    }

    /**
     * 新增一个品牌
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody  Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}")
    public Result deleteBrandById(@PathVariable(value = "id") Integer id){
        brandService.deleteBrandById(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据id更新
     * @param id
     * @param brand
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public Result updateBrandById(@PathVariable Integer id, @RequestBody Brand brand){
        //把id给brand
        brand.setId(id);
        brandService.updateBrandById(brand);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 多条件搜索
     * @param brand
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findBrandList(@RequestBody Brand brand){
        List<Brand> brandList = brandService.findBrandList(brand);
        return  new Result<List<Brand>>(true, StatusCode.OK, "搜索成功", brandList);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findBrandListByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size){
        PageInfo<Brand> brandListByPage = brandService.findBrandListByPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询成功", brandListByPage);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findBrandListByPageAndSearch(@RequestBody Brand brand, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size){
        PageInfo<Brand> brandListByPageAndSearch = brandService.findBrandListByPageAndSearch(brand, page, size);
        return  new Result<>(true, StatusCode.OK, "条件分页查询", brandListByPageAndSearch);
    }
}
