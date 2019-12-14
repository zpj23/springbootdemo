package com.my.blog.website;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
/**
 * SpringBootApplication开启了Spring的组件扫描和Spring Boot的自动配置功能。
 * 实际上，@SpringBootApplication将三个有用的注解组合在了一起。
 * Spring的@Configuration：标明该类使用Spring基于Java的配置。倾向于使用基于Java而不是XML的配置
 *Spring的@ComponentScan：启用组件扫描，这样你写的Web控制器类和其他组件才能被
 * 自动发现并注册为Spring应用程序上下文里的Bean。本章稍后会写一个简单的Spring MVC
 * 控制器，使用@Controller进行注解，这样组件扫描才能找到它
 *Spring Boot 的 @EnableAutoConfiguration ：这个不起眼的小注解也可以称为
 * @Abracadabra①，就是这一行配置开启了Spring Boot自动配置的魔力，让你不用再写成
 * 篇的配置了
 */


@MapperScan("com.my.blog.website.dao")
@SpringBootApplication
@EnableTransactionManagement
public class CoreApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
