<%@page import="com.PAF.item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="item.js"></script>
<script src="jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Project Submission</h1>
<form id="formItem" name="formItem">
 Project:
 <input id="itemCode" name="itemCode" type="text"
 class="form-control form-control-sm">
 <br> Owner Name:
 <input id="itemName" name="itemName" type="text"
 class="form-control form-control-sm">
 <br> Duration:
 <input id="itemPrice" name="itemPrice" type="text"
 class="form-control form-control-sm">
 <br> Email:
 <input id="itemDesc" name="itemDesc" type="text"
 class="form-control form-control-sm">
 <br>Decription:
 <input id="itemPrice" name="itemPrice" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 item itemObj = new item();
 out.print(itemObj.readItems());
 %>
</div>
</div> </div> </div>
</body>
</html>