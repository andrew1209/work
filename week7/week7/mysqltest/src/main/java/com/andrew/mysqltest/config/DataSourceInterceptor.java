package com.andrew.mysqltest.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Component
public class DataSourceInterceptor {

    @Before("execution(* com.andrew.mysqltest.service.*.findAll(..)))")
    public void setRead(){
        DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.slave);
            log.info("dataSource切换到：slave");
    }

    @Before("execution(* com.andrew.mysqltest.service.*.add(..)))")
    public void setWrite(){
        DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.master);
            log.info("dataSource切换到：master");
    }

    @AfterReturning("execution(* com.andrew.mysqltest.service.*.*(..))")
    public void clearDataSourceType() {
        DataBaseContextHolder.clean();
        log.info("dataSource清除：sourceType");
    }
}
