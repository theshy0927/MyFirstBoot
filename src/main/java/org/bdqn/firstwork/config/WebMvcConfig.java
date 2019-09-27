package org.bdqn.firstwork.config;


import org.bdqn.firstwork.utils.CommitedIntercept;
import org.bdqn.firstwork.utils.TokenIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		registry.addInterceptor(tokenIntercept()).addPathPatterns("/user/*");
	}
}
