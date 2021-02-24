/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.controller;

import com.nagarro.images.web.dao.ImageDAO;
import com.nagarro.images.web.services.UploadHelper;
import com.nagarro.images.web.services.SearchImageService;
import com.nagarro.images.web.model.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Laura
 */
public class ImagesController extends HttpServlet {

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
        List<Image> imagesList = SearchImageService.getAll();
        request.getSession().setAttribute("imagesList", imagesList);

        response.sendRedirect("images.jsp");
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
        List<Image> imagesList = new ArrayList<>();
        final HttpSession session = request.getSession();
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        String parameter = request.getParameter("command");
        parameter = "2";
        int command = (isMultiPart) ? 1 : Integer.valueOf(parameter);

        switch (command) {
            case 1: {// case is upload image

                if (isMultiPart) {
                    Image uploadedImage = UploadHelper.uploadFileFrom(request);
                    ImageDAO.getInstance().save(uploadedImage);
                    imagesList.addAll(SearchImageService.getAll());
                }
            }
            break;
            case 2: {// case is cancel 
                response.sendRedirect("index.jsp");
            }
            break;
            case 3: {// case is search 
                String searchText = request.getParameter("searchText");
                imagesList.addAll(SearchImageService.searchBy(searchText));
            }
            case 4: {// case is delete img
                String sImageId = request.getParameter("selectedImage");
                ImageDAO dao = ImageDAO.getInstance();
                dao.delete(Long.valueOf(sImageId));
                doGet(request, response);
            }
            break;
        }

        session.setAttribute("imagesList", imagesList);
        response.sendRedirect("images.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
