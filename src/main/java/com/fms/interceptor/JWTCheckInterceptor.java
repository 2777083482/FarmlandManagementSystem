package com.fms.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fms.context.BaseContext;
import com.fms.constant.JWTConstant;
import com.fms.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("JWTCheckInterceptor")
@Slf4j
public class JWTCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        String token = request.getHeader("token");
        try {
            log.info("jwt校验:{}", token);
            DecodedJWT verifiedToken = JwtUtil.verifyToken(token);
            Integer userid = verifiedToken.getClaim(JWTConstant.USERID).asInt();
            log.info("当前用户id：{}", userid);
            BaseContext.setCurrentId(userid);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
