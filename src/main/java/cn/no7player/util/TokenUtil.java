package cn.no7player.util;

/**
 * Created by liying
 * Date 2020-12-15
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    //过期时间设置(30分钟)
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    //私钥设置(随便乱写的)
    private static final String TOKEN_SECRET = "5xcJVrXNyQDIxK1l2RS9nw";

    public String getToken(Token token) {
        //过期时间和加密算法设置
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = null;
        Map<String, Object> header = new HashMap<>(2);
        try {
            algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //头部信息
            header.put("typ", "JWT");
            header.put("alg", "HS256");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JWT.create()
                .withHeader(header)
                .withClaim("openId", token.getOpenId())
                .withClaim("role", token.getRole())
                .withClaim("lastLogin", token.getLastLogin())
                .withExpiresAt(date)
                .sign(algorithm);

    }

    public Token getTokenData(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Token tk = new Token();

        tk.setOpenId(jwt.getClaim("openId").asString());
        tk.setRole(jwt.getClaim("role").asString());
        tk.setLastLogin(jwt.getClaim("lastLogin").asDate());

        return tk;
    }

    public String creatToken(String openid, String role) {
        //这里是传入的是token对象，决定token的内容
        Token tk = new Token();
        //获取时间用
        Date date = new Date();

        tk.setOpenId(openid);
        tk.setRole(role);
        tk.setLastLogin(date);
        //交给上面的实现类得到token
        return getToken(tk);
    }

    public String getTokenDataOpenId(String token) {
        return JWT.decode(token).getClaim("openId").asString();
    }
}
