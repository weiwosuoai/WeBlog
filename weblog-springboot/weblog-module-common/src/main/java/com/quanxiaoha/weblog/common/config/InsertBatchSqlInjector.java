package com.quanxiaoha.weblog.common.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-06-13 22:46
 * @description: TODO
 **/
public class InsertBatchSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        // super.getMethodList() 保留 Mybatis Plus 自带的方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        // 添加自定义方法：批量插入，方法名为 insertBatchSomeColumn
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
