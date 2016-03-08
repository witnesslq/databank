<%
String location = request.getContextPath() + "/query/query";

response.resetBuffer();
response.setStatus(HttpServletResponse.SC_FOUND);
response.setHeader("Location", location);
%>