package com.yzg.modules.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzg.modules.test.dao.TestDao;
import com.yzg.modules.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:15
 **/
@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestDao, Map> implements TestService {

}
