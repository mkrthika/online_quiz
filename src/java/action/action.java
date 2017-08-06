/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**@WebServlet(urlPatterns={"/action"})
 *
 * @author sun
 */
public class action extends HttpServlet {
    String message,Seat_no,Name,ans1;
    int Total=0;
    java.sql.Connection connect;
    java.sql.Statement stmt=null;
    java.sql.ResultSet rs=null;
    

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet action</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+message+ "</h1>");
            out.println("<h3>Yours results stored in db</h3>");
            out.println("<br><br>");
            out.println("<b>"+"Particpants and their marks"+"</b>");
out.println("<table border=5>");
java.sql.Statement stm2=connect.createStatement();
String query1="SELECT*FROM student";
rs=stm2.executeQuery(query1);
out.println("<th>Seat_no</th>");
out.println("<th>Name</th>");
out.println("<th>Marks</th>");
while(rs.next())
{
    out.println("<td>"+rs.getString(1)+"</td>");
    out.println("<td>"+rs.getString(2)+"</td>");
    out.println("<td>"+rs.getString(3)+"</td>");
    out.println("</tr>");
}
out.println("</table>");
if(rs!=null)
    rs.close();
if(stmt!=null)
    stmt.close();
if(connect!=null)
    stmt.close();
out.println("</center>");
 out.println("</body>");
            out.println("</html>");
            Total=0;
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

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
      try(PrintWriter out= response.getWriter()){
          String url="jdbc:derby://localhost:1527/student [student on STUDENT]";
          try{
              Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
              connect=DriverManager.getConnection(url,"student","student");
              message="Thank for you participating in online Exam";
              Seat_no=request.getParameter("Seat_no");
              Name=request.getParameter("Name");
              ans1=request.getParameter("group1");
              if(ans1.equals("True"))
                  Total+=1;
              stmt=connect.createStatement();
                  String query="INSERT INTO STUDENT.student(seatno,name,total)VALUES('"+Seat_no+"','"+Name+"','"+Total+"')";
                stmt.executeUpdate(query);
                stmt.executeUpdate(query);
                stmt.close();
                 response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet action</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+message+ "</h1>");
            out.println("<h3>Yours results stored in db</h3>");
            out.println("<br><br>");
            out.println("<b>"+"Particpants and their marks"+"</b>");
out.println("<table border=5>");
java.sql.Statement stm2=connect.createStatement();
String query1="SELECT*FROM student";
rs=stm2.executeQuery(query1);
out.println("<th>Seat_no</th>");
out.println("<th>Name</th>");
out.println("<th>Marks</th>");
while(rs.next())
{
    out.println("<td>"+Name+"</td>");
    out.println("<td>"+rs.getString(2)+"</td>");
    out.println("<td>"+rs.getString(3)+"</td>");
    out.println("</tr>");
}
out.println("</table>");
if(rs!=null)
    rs.close();
if(stmt!=null)
    stmt.close();
if(connect!=null)
    stmt.close();
out.println("</center>");
 out.println("</body>");
            out.println("</html>");
            Total=0;
         } catch (SQLException ex) {
              Logger.getLogger(action.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(action.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
              Logger.getLogger(action.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              Logger.getLogger(action.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
}
//        processRequest(request, response);
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

//}
