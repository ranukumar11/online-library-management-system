import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordRecoveryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String newPassword = md5(request.getParameter("newpassword"));

        Connection conn = null;
        PreparedStatement query = null;
        PreparedStatement chngpwd1 = null;
        ResultSet results = null;

        try {
            // Assuming you have a method to get a database connection
            conn = getConnection();
            String sql = "SELECT EmailId FROM tblstudents WHERE EmailId=? and MobileNumber=?";
            query = conn.prepareStatement(sql);
            query.setString(1, email);
            query.setString(2, mobile);
            results = query.executeQuery();

            if (results.next()) {
                String con = "UPDATE tblstudents SET Password=? WHERE EmailId=? and MobileNumber=?";
                chngpwd1 = conn.prepareStatement(con);
                chngpwd1.setString(1, newPassword);
                chngpwd1.setString(2, email);
                chngpwd1.setString(3, mobile);
                chngpwd1.executeUpdate();
                response.getWriter().println("<script>alert('Your Password successfully changed');</script>");
            } else {
                response.getWriter().println("<script>alert('Email id or Mobile no is invalid');</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (results != null) results.close();
                if (query != null) query.close();
                if (chngpwd1 != null) chngpwd1.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        // Implement your database connection logic here
        return null;
    }
}

