<%@page  import="java.sql.*" %>
<%
        String n = null;
        String e = null;
        Class.forName("com.mysql.jdbc.Driver");
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent", "hyper", "hyper");

        
       PreparedStatement stmt = conn.prepareStatement("SELECT * FROM temporary");
        ResultSet rs1 = stmt.executeQuery();
        if(rs1.next()){
            n=rs1.getString("name");
            e=rs1.getString("email");
            
        }
       
        %>