package com.asset.config;

import com.asset.bean.RespBean;
import com.asset.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springblade.core.tool.api.R;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @author hjhu
 * @since 2019-07-23
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getAccountName(),
                            user.getPwd(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("accountName", ((User) auth.getPrincipal()).getAccountName());
        map.put("roles", ((User) auth.getPrincipal()).getRoles());
        map.put("userId", ((User) auth.getPrincipal()).getId());
        map.put("admin", ((User) auth.getPrincipal()).getAdmin());
        map.put("realName", ((User) auth.getPrincipal()).getRealName());
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getAccountName())
//                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.setContentType("application/json;charset=utf-8");
        map.put("Authorization", "Bearer " + token);
        map.put("Expire", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)));
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = res.getWriter();
        res.addHeader("Authorization", "Bearer " + token);
        out.write(om.writeValueAsString(R.data(map)));
        out.flush();
        out.close();
    }

    /**
     * 登录失败时执行下面的方法
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setContentType("application/json;charset=utf-8");
        RespBean respBean = RespBean.error(500, "Logon Failed!", failed.getMessage());
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
