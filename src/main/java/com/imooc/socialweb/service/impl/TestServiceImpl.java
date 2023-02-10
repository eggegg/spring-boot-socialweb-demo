package com.imooc.socialweb.service.impl;

import com.imooc.socialweb.pojo.Test;
import com.imooc.socialweb.mapper.TestMapper;
import com.imooc.socialweb.service.TestService;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
