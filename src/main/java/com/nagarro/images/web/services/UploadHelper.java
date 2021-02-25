/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.services;

import com.nagarro.images.web.model.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Laura
 */
public class UploadHelper {

    static DiskFileItemFactory factory = new DiskFileItemFactory();
    static String filePath = "/Users/Laura.Barragan/documents/temp";

    static {
        factory.setSizeThreshold(40000000);
        factory.setRepository(new File(filePath));
    }
    static ServletFileUpload upload = new ServletFileUpload(factory);

    static {
        upload.setSizeMax(10000000);
    }

    public static Image uploadFileFrom(Map<String, FileItem> request) {
        Image image = new Image();

        File file;

        try {
            FileItem fileItem = request.get("file");
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

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return image;
    }

    public static Map<String, FileItem> getFromMultipart(HttpServletRequest request) {
        Map<String, FileItem> results = new HashMap<>();
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                String fieldName = fileItem.getFieldName();
                results.put(fieldName, fileItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

}
