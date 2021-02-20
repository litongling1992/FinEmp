/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Emp
 * Author:   Administrator
 * Date:     2020/8/30 12:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@Data
@Accessors(chain=true)
public class Emp implements Serializable {
    public String id ;
    public String name;
    public String type;
    public String computer;
    public String mouse;
    public String other;

}
