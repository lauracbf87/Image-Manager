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
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
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
        String sCommand = null;
        String sImageId = null;
        Map<String, FileItem> params = null;
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {
            params = UploadHelper.getFromMultipart(request);
            sCommand = params.get("command").getString();
            sImageId = params.get("selectedImage").getString();
        } else {
            sCommand = request.getParameter("command");
        }
        int command = Integer.valueOf(sCommand);

        switch (command) {
            case 1: {// case is upload image

                if (isMultiPart) {
                    Image uploadedImage = UploadHelper.uploadFileFrom(params);
                    ImageDAO.getInstance().save(uploadedImage);
                    imagesList = SearchImageService.getAll();
                    session.setAttribute("imagesList", imagesList);
                    response.sendRedirect("images.jsp");
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
            break;
            case 4: {// case is delete img

                ImageDAO dao = ImageDAO.getInstance();
                dao.delete(Long.valueOf(sImageId));
                imagesList = SearchImageService.getAll();
                session.setAttribute("imagesList", imagesList);
                response.sendRedirect("images.jsp");
            }
            break;
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
    }

}
