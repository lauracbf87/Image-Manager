/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.services;

import com.nagarro.images.web.model.Image;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Laura
 */
public class UploadHelper {

    public static Image uploadFileFrom(HttpServletRequest request) {
        Image image = new Image();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        String filePath = "/Users/Laura.Barragan/documents/temp";
        File file;

        factory.setSizeThreshold(40000000);
        factory.setRepository(new File(filePath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(10000000);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fileItem = (FileItem) i.next();
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    long sizeInBytes = fileItem.getSize();
                    if (fileName.lastIndexOf("/") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("/")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("/") + 1));
                    }
                    fileItem.write(file);
                    image.setImageName(fileName);
                    image.setImageSize(sizeInBytes);
                    image.setImagePath(filePath);
                    image.setImageType(contentType);
                    System.out.println("Uploaded Filename: " + fileName);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return image;
    }

}
