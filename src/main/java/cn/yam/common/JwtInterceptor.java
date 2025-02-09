package cn.yam.common;

import cn.yam.domain.Users;
import cn.yam.exception.ServiceException;
import cn.yam.mapper.UsersMapper;
import cn.yam.utils.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：
 * 日期：2024/8/14 下午4:10
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UsersMapper usersMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("Authorization");
        // 去掉Authorization前面的Bearer
        if (StrUtil.isNotBlank(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("Authorization");
        }
        //3、如果请求头和参数里面都没有则抛出异常返回信息“请登录” 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }
        //4、如果有token的话   token里面存储的是字符串所以定义的时候是string getAudience()可以存储信息 获取 token 中的 userid  JWT.decode(token)解码解码完成之后拿到getAudience()里面的第一个数据get(0)
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "请登录");
        }
        //5、 根据token中的userid查询数据库进行信息校验Integer.valueOf(userId)把字符串转化为数字然后查询数据库
        Users user = usersMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException("401", "请登录");
        }
        //6、 用户密码加签验证：通过user拿到密码 jwtVerifier是一个验证器，通过用户密码加密之后生成的一个验证器 通过验证器的verify方法继续验证token，如果验证通过return true，如果验证失败则跑一个异常
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "请登录");
        }
        return true;
    }
}