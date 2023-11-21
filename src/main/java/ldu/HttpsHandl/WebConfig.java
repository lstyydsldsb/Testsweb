package ldu.HttpsHandl;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new LoginFilter())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/loading")
                .excludePathPatterns("/load"); // 排除登录页面
    }
}
