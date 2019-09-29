package hcloud.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(filterName = "RequestFilter", urlPatterns = {"/*"})
public class RequestFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);

    public RequestFilter() {
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        ServletRequest requestWrapper = new RequestReader(httpServletRequest);
        chain.doFilter(requestWrapper, response);
    }
    @Override
    public void destroy() {
    }
}
