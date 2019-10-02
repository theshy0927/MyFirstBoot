package org.bdqn.firstwork.config;


import org.bdqn.firstwork.utils.TokenIntercept;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public TokenIntercept tokenIntercept() {
		return new TokenIntercept();
	}
//	@Bean
//	public CommitedIntercept commitedIntercept() {
//		return new CommitedIntercept();
//	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(commitedIntercept()).addPathPatterns("/question/*");
		registry.addInterceptor(tokenIntercept()).addPathPatterns("/user/**");
	}
	
//	@Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//
////        //第一种：java7 常规写法
////        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
////            @Override
////            public void customize(ConfigurableWebServerFactory factory) {
////                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
////                factory.addErrorPages(errorPage404);
////            }
////        };
//        //第二种写法：java8 lambda写法
//        return (factory -> {
//            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/static/404.html");
//            factory.addErrorPages(errorPage404);
//        });
//    }
}
