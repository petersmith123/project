package com.project.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SerletListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("*************destroy ContextListener*************");
    }

    @SuppressWarnings("unused")
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("*************init ContextListener*************");
        ServletContext servletContext = event.getServletContext();
        servletContext.setInitParameter("name","peter");
        System.out.println("-------------------");
        System.out.println("key:"+servletContext.getInitParameter("contextConfigLocation"));
    }
}
