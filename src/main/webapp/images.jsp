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
        <script type="text/javascript">
            
                var setCommand = (event, iCommand) => {
                    
                    var cmd = document.getElementById("command");
                    cmd.value = iCommand;
                    
                }
            
        </script>
    </head>
    <body>
        <jsp:useBean id="imagesList" scope="session" class="java.util.ArrayList" />
        <div>
            <h1>Image Management Utility</h1>
        </div>
        <div>
            <form name="form" action="images" method="POST" enctype="multipart/form-data">
                <table>
                    <thead>
                        <tr>
                            <td>
                                <p>Please select an image file to upload (Max Size 1MB)</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" name="command" id="command" value="1">
                                <input type="file" name="fileName">
                            </td>
                            <td>
                                <input type="submit" onclick="setCommand(this, 1)" value="Submit">
                            </td>
                            <td>
                                <input type="submit" onclick="setCommand(this, 2)" value="Cancel">
                            </td>
                            <td>
                                <input type="submit" onclick="setCommand(this, 3)" value="Search">
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>S.No</td>
                            <td>Name</td>
                            <td>Size</td>
                            <td>Preview</td>
                            <td>Actions</td>
                        </tr>
                        <% for (Image img : (java.util.ArrayList<Image>) imagesList) {%>
                        <tr>
                            <td><%=img.getImageId()%></td>
                            <td><%=img.getImageName()%></td>
                            <td><%=img.getImageSize()%></td>
                            <td><img src="<%=img.getImagePath()%>"/>
                            </td>
                            <td><img src="edit.jpg"/>
                                <img src="delete.jpg"/>
                                <%=img.getImageId()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </form>
        </div>



    </body>
</html>
