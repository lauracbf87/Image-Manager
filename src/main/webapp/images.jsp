<%-- 
    Document   : images
    Created on : 18-feb-2021, 13:08:50
    Author     : Laura
--%>

<%@page import="com.nagarro.images.web.model.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="CSS/styleImage.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/1e6b10ec84.js" crossorigin="anonymous"></script>
        <script src="javascript/images.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:useBean id="imagesList" scope="session" class="java.util.ArrayList" />
        <div>
            <h1>Image Management Utility</h1>
        </div>
        <div>
            <form name="form" id='form' action="images" method="POST" enctype="multipart/form-data">
                <table>
                    <thead>
                        <tr>
                            <td>
                                <p>Please select an image file to upload (Max Size 1MB)</p>
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="hidden" name="command" id="command" value="1">
                                <input type="hidden" name="selectedImage" id="selectedImage" value="-1">
                                
                                <input type="file" name="file">
                            </td>
                            <td>
                                <input type="submit" onclick="setCommand(1)" name="upload" value="Upload">
                            </td>
                            <td>
                                <input type="submit" onclick="setCommand(2)" name="cancel" value="Cancel">
                            </td>
                        </tr>
                    </tbody>  
                </table>
                
                <h4>
                    Uploaded Images
                </h4>
                <table>
                    <thead>
                        <tr>
                            <td>S.No</td>
                            <td>Name</td>
                            <td>Size</td>
                            <td>Preview</td>
                            <td>Actions</td>
                        </tr> 
                    </thead>
                    <tbody>
                        <% for (Image img : (java.util.ArrayList<Image>) imagesList) {%>
                        <tr>
                            <td><%=img.getImageId()%></td>
                            <td><%=img.getImageName()%></td>
                            <td><%=img.getImageSize()%></td>
                            <td><img src="/images-web/displayImg?imageId=<%=img.getImageId()%>" style="max-width: 40px; width: auto;"/>
                            </td>
                            <td>
                                <a onclick="setCommand2(4,<%=img.getImageId()%>)"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </form>
        </div>



    </body>
</html>
