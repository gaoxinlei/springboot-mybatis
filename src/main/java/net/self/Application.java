package net.self;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 自配版启动类
 * 启动类上,需要至少以下注解:
 * @EnableAutoConfiguration,自动配置.
 * @Application 应用.
 * @ComponentScan 不加值表示扫描本包及子包下的所有类.
 * @MapperScan
 * 方法上,需要@Bean注解.
 * @author ezgaoxi
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"net.self"})
@MapperScan("net.self.mapper")
public class Application {

	//读取配置文件的属性
	//共四种方式:http://www.imooc.com/article/18252
	//一、@ConfigurationProperties方式(个人认为需要在配置文件中配置复杂对象如list或map或有级联对象属性时用它.
	//二、使用@Value注解方式(只是简单字符串适用).
	//三、使用Environment(同样适用于简单字符串).
	//四、使用PropertiesLoaderUtils(个人认为简单字符串适用),复杂.
	//测试用value注解和environment.
	@Value("spring.datasource.url")
	private String url;
	
	//测试使用Environment方式获取属性
	@Autowired
	private Environment env;
	//DataSource
	
	//使用ConfigurationProperties导入配置在application.properties中的数据源信息
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		//使用appach下的数据源.
		//http://blog.csdn.net/wangb_java/article/details/74858013
		PoolProperties properties = new PoolProperties();
		properties.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull");
		properties.setDriverClassName("com.mysql.jdbc.Driver");
		properties.setUsername("root");
		properties.setPassword("root");
		System.out.println("测试读取配置文件.");
		System.out.println("@Value注解方式读取url:"+url);
		System.out.println("自动注入Environment方式读取url:"+env.getProperty("spring.datasource.url"));
		return new org.apache.tomcat.jdbc.pool.DataSource(properties);
	}
	
	//SqlSessionFactory
	@Bean
	public SqlSessionFactory sqlSessionfactoryBean() throws Exception {
		//1.如xml配置文件一般,创建factoryBean.
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//2.设置数据源.
		bean.setDataSource(dataSource());
		//3.设置mapperLocations.为包下.
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:/net/self/mapper/*.xml"));
		return bean.getObject();
	}
	//事务管理 TranSactionManager
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	/**
	 * 运行服务
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
