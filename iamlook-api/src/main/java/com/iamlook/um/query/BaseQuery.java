package com.iamlook.um.query;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: Created by youxun
 * @create: 2018-12-17 18:15
 **/
@Setter
@Getter
public class BaseQuery {

    //当前页码
    private int pageNo = 1;
    //每页条数
    private int pageSize = 20;
    // 降序字段
    private String orderByDsc;
    // 升序字段
    private String orderByAsc;

    public Page buildPage() {
        Page tPage =new Page(this.pageNo,this.pageSize);
        if(StringUtils.isNotEmpty(this.orderByDsc)){
            tPage.setOrderByField(this.orderByDsc);
            tPage.setAsc(false);
        }
        if(StringUtils.isNotEmpty(this.orderByAsc)){
            tPage.setOrderByField(this.orderByAsc);
            tPage.setAsc(true);
        }
        return tPage;
    }
}
