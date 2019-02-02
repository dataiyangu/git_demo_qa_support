package com.ilcuky.springboot.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilcuky.springboot.MainTest;

/**
 * @author IluckySi
 */
public class GetServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * http://localhost:8080/ilucky-springboot-1.5.7-0.0.1-SNAPSHOT/rest/test
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GetServlet");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            MainTest.main(null);
            out.write("success:");
        } finally {
            out.close();
        }
    }
}
