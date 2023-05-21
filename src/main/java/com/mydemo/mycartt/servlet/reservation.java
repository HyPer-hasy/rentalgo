/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mydemo.mycartt.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

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
public class reservation extends HttpServlet {

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
            out.println("<title>Servlet reservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reservation at " + request.getContextPath() + "</h1>");
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
    String pickuplocation = request.getParameter("pickuplocation");
    String pickupdate = request.getParameter("pickupdate");
    String pickuptime = request.getParameter("pickuptime");
    String droplocation = request.getParameter("droplocation");
    String dropdate = request.getParameter("dropdate");
    String droptime = request.getParameter("droptime");
    String gender = request.getParameter("gender");
    String dob = request.getParameter("dob");
    String age = request.getParameter("age");
    String address = request.getParameter("address");
    String zip = request.getParameter("zip");
    String carname = request.getParameter("carname");
    String carprice = request.getParameter("carprice");
    String noofdays = request.getParameter("noofdays");
    String insuranceplancharge = request.getParameter("insuranceplancharge");
    String shippingcharge = request.getParameter("shippingcharge");
    String gst = request.getParameter("gst");
    String discount = request.getParameter("discount");
    String ordertotal = request.getParameter("ordertotal");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    
    RequestDispatcher dispatch = null;
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent", "hyper", "hyper");

        String sql = "INSERT INTO orders(pickuplocation, pickupdate, pickuptime, droplocation, dropdate, droptime, gender, dob, age, address, zipcode, car_name, car_price, noofdays, insurance_charge, shipping_charge, gst, discount, order_total, name, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, pickuplocation);
        stmt.setString(2, pickupdate);
        stmt.setString(3, pickuptime);
        stmt.setString(4, droplocation);
        stmt.setString(5, dropdate);
        stmt.setString(6, droptime);
        stmt.setString(7, gender);
        stmt.setString(8, dob);
        stmt.setString(9, age);
        stmt.setString(10, address);
        stmt.setString(11, zip);
        stmt.setString(12, carname);
        stmt.setString(13, carprice);
        stmt.setString(14, noofdays);
        stmt.setString(15, insuranceplancharge);
        stmt.setString(16, shippingcharge);
        stmt.setString(17, gst);
        stmt.setString(18, discount);
        stmt.setString(19, ordertotal);
        stmt.setString(20, name);
        stmt.setString(21, email);
        
        
        
        
        int rowcount = stmt.executeUpdate();
        if (rowcount > 0) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }
        dispatch = request.getRequestDispatcher("conf_page.jsp");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
        System.out.println(e);
        request.setAttribute("status", "failed");
        
        dispatch = request.getRequestDispatcher("fail.jsp");
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
    
    
    String myname = name;
        // Recipient's email ID needs to be mentioned.
        String to =  email;
       
        // Sender's email ID needs to be mentioned
        String fromEmail = "tarunshah645@gmail.com";
        String fromName = "Drive Wheel";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties object
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Setup password authentication
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tarunshah645@gmail.com", "fiyiispgmyoapojm");
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(fromEmail, fromName));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the subject");
              String htmlContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"><head><meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"><meta charset=\"UTF-8\"><meta name=\"x-apple-disable-message-reformatting\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta content=\"telephone=no\" name=\"format-detection\"><title>New message</title><link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\"><style type=\"text/css\">#outlook a{ padding: 0;} .ExternalClass{ width: 100%;} .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{ line-height: 100%;} .es-button{ mso-style-priority: 100 !important; text-decoration: none !important;} a[x-apple-data-detectors]{ color: inherit !important; text-decoration: none !important; font-size: inherit !important; font-family: inherit !important; font-weight: inherit !important; line-height: inherit !important;} .es-desk-hidden{ display: none; float: left; overflow: hidden; width: 0; max-height: 0; line-height: 0; mso-hide: all;} @media only screen and (max-width:600px){ p, ul li, ol li{ margin-bottom: 10px !important} .es-header-body p, .es-header-body ul li, .es-header-body ol li{ margin-bottom: 10px !important} .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li{ margin-bottom: 10px !important} .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li{ margin-bottom: 8px !important} p, ul li, ol li, a{ line-height: 120% !important} h1, h2, h3, h1 a, h2 a, h3 a{ line-height: 120% !important} h1{ font-size: 32px !important; text-align: center; margin-bottom: 20px} h2{ font-size: 26px !important; text-align: center; margin-bottom: 16px} h3{ font-size: 20px !important; text-align: center; margin-bottom: 12px} .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a{ font-size: 32px !important} .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a{ font-size: 26px !important} .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a{ font-size: 20px !important} .es-menu td a{ font-size: 16px !important} .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a{ font-size: 16px !important} .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a{ font-size: 16px !important} .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a{ font-size: 16px !important} .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a{ font-size: 12px !important} *[class=\"gmail-fix\"]{ display: none !important} .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3{ text-align: center !important} .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3{ text-align: right !important} .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3{ text-align: left !important} .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img{ display: inline !important} .es-button-border{ display: inline-block !important} a.es-button, button.es-button{ font-size: 16px !important; display: inline-block !important} .es-btn-fw{ border-width: 10px 0px !important; text-align: center !important} .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right{ width: 100% !important} .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header{ width: 100% !important; max-width: 600px !important} .es-adapt-td{ display: block !important; width: 100% !important} .adapt-img{ width: 100% !important; height: auto !important} .es-m-p0{ padding: 0 !important} .es-m-p0r{ padding-right: 0 !important} .es-m-p0l{ padding-left: 0 !important} .es-m-p0t{ padding-top: 0 !important} .es-m-p0b{ padding-bottom: 0 !important} .es-m-p20b{ padding-bottom: 20px !important} .es-mobile-hidden, .es-hidden{ display: none !important} tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden{ width: auto !important; overflow: visible !important; float: none !important; max-height: inherit !important; line-height: inherit !important} tr.es-desk-hidden{ display: table-row !important} table.es-desk-hidden{ display: table !important} td.es-desk-menu-hidden{ display: table-cell !important} .es-menu td{ width: 1% !important} table.es-table-not-adapt, .esd-block-html table{ width: auto !important} table.es-social{ display: inline-block !important} table.es-social td{ display: inline-block !important} .es-desk-hidden{ display: table-row !important; width: auto !important; overflow: visible !important; max-height: inherit !important} .es-m-p5{ padding: 5px !important} .es-m-p5t{ padding-top: 5px !important} .es-m-p5b{ padding-bottom: 5px !important} .es-m-p5r{ padding-right: 5px !important} .es-m-p5l{ padding-left: 5px !important} .es-m-p10{ padding: 10px !important} .es-m-p10t{ padding-top: 10px !important} .es-m-p10b{ padding-bottom: 10px !important} .es-m-p10r{ padding-right: 10px !important} .es-m-p10l{ padding-left: 10px !important} .es-m-p15{ padding: 15px !important} .es-m-p15t{ padding-top: 15px !important} .es-m-p15b{ padding-bottom: 15px !important} .es-m-p15r{ padding-right: 15px !important} .es-m-p15l{ padding-left: 15px !important} .es-m-p20{ padding: 20px !important} .es-m-p20t{ padding-top: 20px !important} .es-m-p20r{ padding-right: 20px !important} .es-m-p20l{ padding-left: 20px !important} .es-m-p25{ padding: 25px !important} .es-m-p25t{ padding-top: 25px !important} .es-m-p25b{ padding-bottom: 25px !important} .es-m-p25r{ padding-right: 25px !important} .es-m-p25l{ padding-left: 25px !important} .es-m-p30{ padding: 30px !important} .es-m-p30t{ padding-top: 30px !important} .es-m-p30b{ padding-bottom: 30px !important} .es-m-p30r{ padding-right: 30px !important} .es-m-p30l{ padding-left: 30px !important} .es-m-p35{ padding: 35px !important} .es-m-p35t{ padding-top: 35px !important} .es-m-p35b{ padding-bottom: 35px !important} .es-m-p35r{ padding-right: 35px !important} .es-m-p35l{ padding-left: 35px !important} .es-m-p40{ padding: 40px !important} .es-m-p40t{ padding-top: 40px !important} .es-m-p40b{ padding-bottom: 40px !important} .es-m-p40r{ padding-right: 40px !important} .es-m-p40l{ padding-left: 40px !important} .h-auto{ height: auto !important}} </style></head><body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\"><div class=\"es-wrapper-color\" style=\"background-color:#EEEEEE\"><table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#EEEEEE\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" style=\"padding:0;Margin:0\"><table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr style=\"border-collapse:collapse\"></tr><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0\"><table class=\"es-header-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#fd6f03;width:590px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fd6f03\" align=\"center\"><tr style=\"border-collapse:collapse\"><td class=\"es-m-p25\" align=\"left\" style=\"padding:35px;Margin:0\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://gpqmba.stripocdn.email/content/guids/CABINET_24a50142c3953c5c5d3849883ea63e85562715ece9a41ba10c8ae18c52506b76/images/logo.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" height=\"45\" width=\"164\"></td></tr></table></td></tr></table></td></tr></table></td></tr></table><table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0\"><table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:590px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:590px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:30px;Margin:0;font-size:0px\"><img src=\"https://gpqmba.stripocdn.email/content/guids/CABINET_24a50142c3953c5c5d3849883ea63e85562715ece9a41ba10c8ae18c52506b76/images/hand_check_tick256.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" height=\"101\" width=\"101\"></td></tr><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h1 style=\"Margin:0;line-height:43px;mso-line-height-rule:exactly;font-family:'times new roman', times, baskerville, georgia, serif;font-size:36px;font-style:normal;font-weight:bold;color:#333333;margin-bottom:22px\">" + myname +"</h1><h3 style=\"Margin:0;line-height:22px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:18px;font-style:normal;font-weight:normal;color:#333333;margin-bottom:11px\">Congrats !&nbsp; YOUR CAR IS BOOKED&nbsp;/h3></td></tr><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:17px;margin-bottom:9px;color:#333333;font-size:14px\"><span style=\"font-family:'lucida sans unicode', 'lucida grande', sans-serif\"><strong><em>Show this at the time of car pickup <br>&amp; <br>Pay the order amount at the pay desk </em></strong></span>&nbsp; </p></td></tr><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h2 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#333333;margin-bottom:18px\">ORDER INVOICE</h2></td></tr></table></td></tr></table></td></tr></table></td></tr></table><table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0\"><table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:590px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:35px;padding-right:35px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td bgcolor=\"#fd6f03\" align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px\"><table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:500px\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"left\" role=\"presentation\"><tr style=\"border-collapse:collapse\"><td width=\"70%\" style=\"padding:0;Margin:0\"><h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">Order No.&nbsp;</h4></td><td width=\"30%\" style=\"padding:0;Margin:0\"><h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">2345678</h4></td></tr></table></td></tr></table></td></tr></table></td></tr><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-left:20px;padding-right:20px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:550px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:25px;Margin:0\"><table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:500px\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"left\" role=\"presentation\"><tr style=\"border-collapse:collapse\"><td style=\"padding:5px 10px 5px 0;Margin:0\" width=\"70%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">" + carname +"</p></td><td style=\"padding:5px 0;Margin:0\" width=\"30%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">&#x20B9; " + carprice +"&nbsp;</p></td></tr><tr style=\"border-collapse:collapse\"><td style=\"padding:5px 10px 5px 0;Margin:0\" width=\"70%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">No.of Days&nbsp;</p></td><td style=\"padding:5px 0;Margin:0\" width=\"30%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">&nbsp; &nbsp; &nbsp;" + noofdays +"&nbsp;</p></td></tr><tr style=\"border-collapse:collapse\"><td style=\"padding:5px 10px 5px 0;Margin:0\" width=\"70%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">&nbsp;GST (9%)</p></td><td style=\"padding:5px 0;Margin:0\" width=\"30%\" align=\"left\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;margin-bottom:9px;color:#333333;font-size:14px\">&#x20B9; " + gst +"</p></td></tr><tr style=\"border-collapse:collapse\"><td style=\"padding:5px 10px 5px 0px;Margin:0;text-align:justify\" width=\"70%\">Handling fees</td><td style=\"padding:5px 0;Margin:0\" width=\"30%\" align=\"left\">&#x20B9; " + shippingcharge +"</td></tr><tr style=\"border-collapse:collapse\"><td style=\"padding:5px 10px 5px 0px;Margin:0;font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol'\" width=\"70%\" align=\"left\">Insurance Plan charge</td><td style=\"padding:5px 0;Margin:0\" width=\"30%\" align=\"left\">&#x20B9; " + insuranceplancharge +"</td></tr></table></td></tr></table></td></tr></table></td></tr><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-left:35px;padding-right:35px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\"><table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;border-top:3px solid #eeeeee;border-bottom:3px solid #eeeeee\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:15px;padding-bottom:15px\"><table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:500px\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"left\" role=\"presentation\"><tr style=\"border-collapse:collapse\"><td width=\"60%\" style=\"padding:0;Margin:0\"><h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">TOTAL</h4></td><td width=\"40%\" style=\"padding:0;Margin:0\"><h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">&#x20B9; " + ordertotal +"</h4></td></tr></table></td></tr></table></td></tr></table></td></tr><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-left:35px;padding-right:35px;padding-top:40px\"><table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"><tr style=\"border-collapse:collapse\"><td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:245px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:30px\"><h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">Pickup Address</h4></td></tr><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;margin-bottom:9px;color:#333333;font-size:14px\">" + pickuplocation +" <br>Date :" + pickupdate +" <br>Time:" + pickuptime +" </p></td></tr></table></td></tr></table><table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"><tr style=\"border-collapse:collapse\"><td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:245px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:15px\"><h4 style=\"Margin:0;line-height:200%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">Drop&nbsp;Address</h4></td></tr><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;margin-bottom:9px;color:#333333;font-size:14px\">" + droplocation +" <br>Date :" + dropdate +" <br>Time:" + droptime +" </p></td></tr></table></td></tr></table></td></tr></table></td></tr></table><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0\"><table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:590px\"><tr style=\"border-collapse:collapse\"><td align=\"left\" style=\"padding:0;Margin:0;padding-left:35px;padding-right:35px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:15px;font-size:0px\"><img src=\"https://gpqmba.stripocdn.email/content/guids/CABINET_24a50142c3953c5c5d3849883ea63e85562715ece9a41ba10c8ae18c52506b76/images/marker.png\" alt=\"Beretun logo\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" title=\"Beretun logo\" width=\"37\" height=\"56\"></td></tr><tr style=\"border-collapse:collapse\"><td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:17px;margin-bottom:9px;color:#333333;font-size:14px\"><strong>6345&nbsp;Massachusetts Avenue </strong></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:17px;margin-bottom:9px;color:#333333;font-size:14px\"><strong>Cambridge, MA 02139</strong></p></td></tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></div></body></html>";
            // Set HTML content
            message.setContent(htmlContent, "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (AddressException e) {
        } catch (MessagingException e) {
        }

}

// mail send code

    


    

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
