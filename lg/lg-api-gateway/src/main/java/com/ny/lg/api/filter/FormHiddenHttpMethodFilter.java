package com.ny.lg.api.filter;

import com.ny.lg.api.utils.JwtTokenUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormHiddenHttpMethodFilter extends HiddenHttpMethodFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getParameter("_header");
        if (!StringUtils.isEmpty(header)) {
            HttpServletRequest wrapper = new FormHiddenHttpMethodFilter.HttpHeaderRequestWrapper(request, header);
            super.doFilterInternal(wrapper, response, filterChain);
        } else {
            super.doFilterInternal(request, response, filterChain);
        }
    }


    class HttpHeaderRequestWrapper extends HttpServletRequestWrapper {
        private final String header;

        public HttpHeaderRequestWrapper(HttpServletRequest request, String token) {
            super(request);
            this.header = token;
        }

        public String getHeader(String name) {
            String authorization = JwtTokenUtils.HEADER_STRING;
            if ((name != null) && (authorization.equals(name)) && (super.getHeader(authorization) == null)) {
                return this.header;
            }
            return super.getHeader(name);
        }
    }
}