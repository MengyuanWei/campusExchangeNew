package com.campus.exchange.filter;


import com.campus.exchange.model.User;
import com.campus.exchange.service.JWTService;
import com.campus.exchange.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecFilter implements Filter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired private Logger logger;

    private static String AUTH_URI = "/auth";


    @Override // Authorization: Bearer <token>
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // outside container find inner container bean
        if (userService == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(
                    this, request.getServletContext());
        }
        // 1. extract Authorization header
        // 2. remove bearer to get token
        // 3. decrypt token to get claim
        // 4. verify username information in our database from claim
        // 5. doFilter dispatch to controller
        HttpServletRequest req = (HttpServletRequest)request;
        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(request, response);
        else ((HttpServletResponse)response).sendError(statusCode);
    }

    private int authorization(HttpServletRequest req){
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        String verb = req.getMethod();
        if(uri.equalsIgnoreCase(AUTH_URI)) return HttpServletResponse.SC_ACCEPTED;

        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;
//
            Claims claims = jwtService.decryptJwtToken(token);
            //TODO pass username and check role
            if(claims.getId()!=null){
                User u = userService.getById(Long.valueOf(claims.getId()));
                if(u==null) return statusCode;
//                statusCode = HttpServletResponse.SC_ACCEPTED;
//                if(u==null) statusCode = HttpServletResponse.SC_ACCEPTED;
            }
            String allowedResources = "/";
            switch(verb) {
                case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
                case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
                case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
                case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
            }
            for (String s : allowedResources.split(",")) {
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }
            logger.debug(String.format("Verb: %s, allowed resources: %s", verb, allowedResources));
        }
        catch (Exception e) {
            logger.error("can't verify the token",e);
        }
        return statusCode;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
