package com.imooc.socialweb.service.impl;

import com.imooc.socialweb.pojo.Category;
import com.imooc.socialweb.mapper.CategoryMapper;
import com.imooc.socialweb.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
