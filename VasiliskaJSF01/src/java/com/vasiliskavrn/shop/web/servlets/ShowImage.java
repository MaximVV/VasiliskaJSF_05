
package com.vasiliskavrn.shop.web.servlets;

import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vasiliskavrn.shop.web.db.DataHelper;
import com.vasiliskavrn.shop.web.entity.Goods;
import com.vasiliskavrn.shop.web.controllers.SearchController;




@WebServlet(name = "ShowImage",
urlPatterns = {"/ShowImage"})
public class ShowImage extends HttpServlet {

    //    private static Map<Long, Byte[]> imageMap = new HashMap();
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        try {
            int index = Integer.valueOf(request.getParameter("index"));
            SearchController searchController = (SearchController) request.getSession(false).getAttribute("searchController");
            byte[] image = ((Goods)searchController.getPager().getList().get(index)).getImage().getImageCotnent();
            response.setContentLength(image.length);
            out.write(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    private Byte[] convertBytes(byte[] bytes) {
        Byte[] imageBytes = new Byte[bytes.length];
        int i = 0;
        for (byte b : bytes) {
            imageBytes[i++] = b;
        }
        return imageBytes;
    }

    private byte[] convertBytes(Byte[] bytes) {
        int i = 0;
        byte[] image = new byte[bytes.length];
        for (Byte b : bytes) {
            image[i++] = b.byteValue();
        }
        return image;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
