package com.ajinkyabhutkar.irctc.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeLoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Long startTime=System.currentTimeMillis();

        Thread.sleep(400);
        System.out.println("time taken before entering request "+startTime);

        request.setAttribute("startTime", startTime);
                return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Long startTime=(Long)request.getAttribute("startTime");
        Long endTime=System.currentTimeMillis();

        Long difference=endTime-startTime;

        System.out.println("Total time taken for execution"+difference+" on URI"+request.getRequestURI());
    }
}
