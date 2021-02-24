/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.services;

import com.nagarro.images.web.dao.ImageDAO;
import com.nagarro.images.web.model.Image;
import java.util.List;

/**
 *
 * @author Laura
 */
public class SearchImageService {

    public static List<Image> searchBy(String searchText) {
        ImageDAO dao = ImageDAO.getInstance();
        
        return dao.searchByText(searchText);
    }

    public static List<Image> getAll() {
        ImageDAO dao = ImageDAO.getInstance();
        
        return dao.getAll();    
    }
    
}
