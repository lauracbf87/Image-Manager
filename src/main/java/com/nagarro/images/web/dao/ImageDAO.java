/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.dao;

import com.nagarro.images.web.model.Image;
import com.nagarro.images.web.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Laura
 */
public class ImageDAO {

    public static ImageDAO getInstance() {
        return new ImageDAO();
    }

    public List<Image> searchByText(String searchText) {
        List<Image> results = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/image-web";
            Connection conn = DriverManager.getConnection(url, "root", "Cristin@8");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM images WHERE name like ?");
            stmt.setString(1, quote(searchText));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setImageId(rs.getLong(1));
                image.setImageName(rs.getString(2));
                image.setImageSize(rs.getLong(3));
                image.setImageType(rs.getString(4));
                image.setImagePath(rs.getString(5));
                results.add(image);
            }

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return results;
    }

    private String quote(String searchText) {
        return "%" + searchText + "%";
    }

    public void save(Image uploadedImage) {
        
        try {
            String url = "jdbc:mysql://localhost:3306/image-web";
            Connection conn = DriverManager.getConnection(url, "root", "Cristin@8");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO IMAGES ("
                    + "IMAGE_NAME, IMAGE_SIZE, IMAGE_TYPE, IMAGE_PATH)"
                    + " VALUES (?, ?, ?, ?)");
            stmt.setString(1, uploadedImage.getImageName());
            stmt.setLong(2, uploadedImage.getImageSize());
            stmt.setString(3, uploadedImage.getImageType());
            stmt.setString(4, uploadedImage.getImagePath());
            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
    }

    public List<Image> getAll() {
     List<Image> results = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/image-web";
            Connection conn = DriverManager.getConnection(url, "root", "Cristin@8");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM images");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setImageId(rs.getLong(1));
                image.setImageName(rs.getString(2));
                image.setImageSize(rs.getLong(3));
                image.setImageType(rs.getString(4));
                image.setImagePath(rs.getString(5));
                results.add(image);
            }

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return results;    
    }
    
}
