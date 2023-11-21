package ldu.HttpsHandl;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("currentUser"));

        // 检查用户是否已登录，这里可以根据自己的业务逻辑进行判断
        if (session.getAttribute("currentUser") == null) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect("/load");
            return false; // 阻止请求继续处理
        }



        return true; // 允许请求继续处理
    }
}
