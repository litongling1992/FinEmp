package com.baizhi.service;

import com.baizhi.entity.Emp;
import com.baizhi.vo.PageRequest;
import com.baizhi.vo.PageResult;

import java.util.List;

public interface EmpService {
    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    Emp findOne(String id);

    void updateOne(Emp emp);

    PageResult findPage(PageRequest pageRequest);
}
