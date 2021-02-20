/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: EmpController
 * Author:   Administrator
 * Date:     2020/8/30 13:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import com.baizhi.vo.PageRequest;
import com.baizhi.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("emp")
@Slf4j
public class EmpController {
    @Autowired
    EmpService empService;

//    @Value("${upload.dir}")
//    private String realPath;

    //查询所有员工信息
    @GetMapping("findAll")
    public List<Emp> findAll(){
        return empService.findAll();
    }

    @GetMapping("findOne")
    public Emp findOne(String id){
        return empService.findOne(id);
    }

    //保存员工信息
    @PostMapping("save")
    public Map<String,Object> save(@RequestBody Emp emp) throws IOException {
        log.info("员工信息: [{}]",emp);


        Map<String,Object> map = new HashMap<>();

        try {
            //头像保存，需要重新规则命名文件


            empService.save(emp);

            map.put("state",true);
            map.put("msg","员工信息保存成功");
        } catch (Exception e) {

            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息保存失败");
        }

        return map;
    }

    @GetMapping("delete")
    public Map<String,Object> delete(String id){
        log.info("删除员工信息! [{}]",id);
        Map<String,Object> map = new HashMap<>();
        try {
            //删除员工信息时，也顺便把头像信息删除掉

            //现根据id来查找用户
           // Emp empUser = empService.findOne(id);

            empService.delete(id);

            map.put("state",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除失败");
        }
        return map;
    }

    //修改员工信息
    @PostMapping("update")
    public Map<String,Object> update(@RequestBody Emp emp ) throws IOException {
        log.info("员工信息: [{}]",emp);

        Map<String,Object> map = new HashMap<>();

        try {

            empService.updateOne(emp);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        } catch (Exception e) {

            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息保存失败");
        }

        return map;
    }

    @PostMapping("findPage")
    @CrossOrigin
    public Map<String,Object> findPage(@RequestBody PageRequest pageQuery) {
        Map<String,Object> map = new HashMap<>();
        try {
            PageRequest p = new PageRequest();
            p.setPageSize(pageQuery.getPageSize());
            p.setPageNum(pageQuery.getPageNum());
            PageResult result =empService.findPage(p);

            map.put("state",true);
            map.put("msg","分页查询成功");
            map.put("result",result.getContent());
            map.put("pageSize",result.getPageSize());
            map.put("totalSize",result.getTotalSize());
            map.put("totalPages",result.getTotalPages());
        } catch (Exception e) {

            e.printStackTrace();
            map.put("state",false);
            map.put("msg","查询失败");
        }

        return map;
    }
}
