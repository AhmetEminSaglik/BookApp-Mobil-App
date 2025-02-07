package org.ahmeteminsaglik.bookapp.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    @Value("${app.api.key}")
    private String apiKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestApiKey = req.getHeader("X-API-KEY");

        if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Unauthorized: Invalid API Key");
            return;
        }

        chain.doFilter(request, response);
    }
}
