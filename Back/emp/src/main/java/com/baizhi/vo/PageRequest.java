/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PageRequest
 * Author:   Administrator
 * Date:     2020/9/7 15:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分页查询请求封装类〉
 *
 * @author Administrator
 * @create 2020/9/7
 * @since 1.0.0
 */
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
