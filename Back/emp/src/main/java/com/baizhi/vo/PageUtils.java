/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PageUtils
 * Author:   Administrator
 * Date:     2020/9/7 15:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.vo;

import com.github.pagehelper.PageInfo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分页查询相关工具类。〉
 *
 * @author Administrator
 * @create 2020/9/7
 * @since 1.0.0
 */
public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
