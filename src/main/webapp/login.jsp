<%-- 
    Document   : login
    Created on : 18-feb-2021, 13:08:36
    Author     : Laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Search Engine</title>
    </head>
    <body>
        <jsp:useBean id="errorMessage" scope="session" class="java.lang.String" />
        <jsp:useBean id="currentUser" scope="session" class="java.lang.String" />
        
        <form name="loginForm" action="login" method="POST">

            <div>
                <h1>Login</h1>
            </div>
            <div>
                <table>
                    <tr>
                        <td><label>Username:</label></td>
                        <td><input type="text" name="userName" value="<%= currentUser %>"></td>
                    </tr>
                    <tr>
                        <td><label>Password:</label></td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="errorMsg">
                            <%= errorMessage %>!!!
                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <button>Login >></button>
            </div>
        </form>
    </body>
</html>
