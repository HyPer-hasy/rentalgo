/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mydemo.mycartt.servlet;


import java.io.IOException;
import java.io.PrintWriter;


import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HYPER
 */
public class register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String nm = request.getParameter("name_n");
    String usr = request.getParameter("user_n");
    String em = request.getParameter("email_n");
    String ph = request.getParameter("phone_n");
    String pss = request.getParameter("pass_n");
    RequestDispatcher dispatch = null;
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
            Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent", "hyper", "hyper");

        String sql = "INSERT INTO signin(nm, usrnm, em, phn, password) VALUES (?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, nm);
        stmt.setString(2, usr);
        stmt.setString(3, em);
        stmt.setString(4, ph);
        stmt.setString(5, pss);

        int rowcount = stmt.executeUpdate();
        if (rowcount > 0) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }
        dispatch = request.getRequestDispatcher("login.jsp");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
        System.out.println(e);
        request.setAttribute("status", "failed");
        
        dispatch = request.getRequestDispatcher("index.jsp");
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dispatch.forward(request, response);
    }
}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
