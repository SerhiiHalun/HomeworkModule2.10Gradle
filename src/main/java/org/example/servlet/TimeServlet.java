package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet( value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        resp.getWriter().write(getTimezone(req));
        resp.getWriter().close();
    }
    private String getTimezone(HttpServletRequest req){
        LocalDateTime time ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(req.getParameterMap().containsKey("timezone")){
            time = LocalDateTime.now(ZoneId.of(req.getParameter("timezone")));
            return time.format(formatter) +" "+ req.getParameter("timezone");
        }
        time = LocalDateTime.now();
        return time.format(formatter) + " UTC";
    }
}
