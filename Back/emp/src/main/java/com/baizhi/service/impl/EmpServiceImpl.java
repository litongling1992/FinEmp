/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: EmpServiceImpl
 * Author:   Administrator
 * Date:     2020/8/30 13:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.service.impl;

import com.baizhi.dao.EmpDao;
import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import com.baizhi.vo.PageRequest;
import com.baizhi.vo.PageResult;
import com.baizhi.vo.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@Service
@Transactional //事务的注解
public class EmpServiceImpl implements EmpService {

    @Resource
    EmpDao empDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }

    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    public Emp findOne(String id) {
        return empDao.findOne(id);
    }

    @Override
    public void updateOne(Emp emp) {
        empDao.updateOne(emp);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {

        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     *
     * @return
     */
    private PageInfo<Emp> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Emp> sysMenus = empDao.findPage();
        return new PageInfo<Emp>(sysMenus);
    }


}
