package org.example.web_filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timezoneParam = req.getParameter("timezone");
        if (timezoneParam == null || timezoneParam.isEmpty() || isValidTimezone(timezoneParam)) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType("text/html; charset=utf-8");
            res.getWriter().write("Invalid timezone");
            res.getWriter().close();
        }
    }

    private boolean isValidTimezone(String timezone) {
        for (String id : TimeZone.getAvailableIDs()) {
            if (id.equals(timezone)) {
                return true;
            }
        }
        return false;


    }
}
