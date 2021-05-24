package com.andrew.mysqltest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Value("${database.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "database.master")
    public DataSource master(){
        DataSource master = DataSourceBuilder.create().type(dataSourceType).build();
        return master;
    }


    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "database.slave")
    public DataSource slave(){
        DataSource slave = DataSourceBuilder.create().type(dataSourceType).build();
        return slave;
    }

    @Bean
    @Primary
    public DataSource routingDataSource(@Qualifier("master") DataSource masterDataSource,@Qualifier("slave") DataSource slaveDataSource){
        Map<Object,Object> targetDataSources =new HashMap<>();
        targetDataSources.put(DataBaseContextHolder.DataBaseType.master,masterDataSource);
        targetDataSources.put(DataBaseContextHolder.DataBaseType.slave,slaveDataSource);
        ReadWriteSplitRoutingDataSource dataSource = new ReadWriteSplitRoutingDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        dataSource.afterPropertiesSet();
        return dataSource;
    }



}
