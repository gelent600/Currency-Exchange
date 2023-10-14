package by.bpc.filter;

import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    private final Logger logger;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Request URI: " + request.getRequestURI());

        filterChain.doFilter(request, response);

        logger.info("Response status: " + response.getStatus());
    }
}

