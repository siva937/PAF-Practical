<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Researcher"%>
<%
//Save---------------------------------
if (request.getParameter("researcherCode") != null)
{
Researcher researcherObj = new Researcher();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidResearcherIDSave") == "")
{
stsMsg = researcherObj.insertResearcher(request.getParameter("researcherCode"),
request.getParameter("researcherName"),
request.getParameter("researcherEmail"),
request.getParameter("researcherDep"));
}
else//Update----------------------
{
stsMsg = researcherObj.updateResearcher(request.getParameter("hidResearcherIDSave"),
request.getParameter("researcherCode"),
request.getParameter("researcherName"),
request.getParameter("researcherEmail"),
request.getParameter("researcherDep"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidResearcherIDDelete") != null)
{
Researcher researcherObj = new Researcher();
String stsMsg =
researcherObj.deleteResearcher(request.getParameter("hidResearcherIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/researcher.js"></script>
<title>Researcher Management</title>
</head>
<body>
<h1>Researcher Management</h1>

<form id="formResearcher" name="formResearcher" method="post" action="researcher.jsp">
 Researcher code:
<input id="researcherCode" name="researcherCode" type="text"
 class="form-control form-control-sm">
<br> Researcher name:
<input id="researcherName" name="researcherName" type="text"
 class="form-control form-control-sm">
<br> Researcher email:
<input id="researcherEmail" name="researcherEmail" type="text"
 class="form-control form-control-sm">
<br> Researcher department:
<input id="researcherDep" name="researcherDep" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidResearcherIDSave" name="hidResearcherIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divItemsGrid">
<%
Researcher researcherObj = new Researcher();
 out.print(researcherObj.readResearcher());
%>
</div>
</body>
</html>