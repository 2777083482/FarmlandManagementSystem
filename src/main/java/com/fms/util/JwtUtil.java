package com.fms.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fms.constant.JWTConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 设置一个密钥
    private static final String SECRET_KEY = JWTConstant.KEY; // 请使用更强的密钥
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY); // HMAC256加密算法

    // 默认过期时间：7天
    private static final long EXPIRE_TIME = 60 * 60 * 1000 * 24 * 7;

    /**
     * 生成Token
     *
     * @param claims 自定义的声明（键值对）
     * @return 生成的Token
     */
    public static String generateToken(Map<String, Object> claims) {
        return JWT.create()
                .withHeader(new HashMap<>()) // 可以添加自定义Header
                .withPayload(claims) // 添加自定义声明
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 设置过期时间
                .sign(ALGORITHM); // 签名算法
    }

    /**
     * 验证Token并解析
     *
     * @param token 需要验证的Token
     * @return DecodedJWT对象，包含解析后的数据
     * @throws JWTVerificationException 如果验证失败，会抛出异常
     */
    public static DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        return verifier.verify(token);
    }

    /**
     * 直接解析Token获取Payload
     *
     * @param token 需要解析的Token
     * @return DecodedJWT对象
     * @throws JWTDecodeException 如果解析失败，会抛出异常
     */
    public static DecodedJWT decodeToken(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }
}
