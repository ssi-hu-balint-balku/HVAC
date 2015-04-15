<%@ page import="com.training.legalmagiccrabs.HvacClient" %>
<%--
  Created by IntelliJ IDEA.
  User: Zoltan_Beke
  Date: 15/04/15
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Data sent summary</title>
  <meta http-equiv="refresh" content="5; url=index.jsp" />
</head>
<body>
Minimum temperature: <%= request.getParameter("rangeInputMin") %> <br/>
Maximum temperature: <%= request.getParameter("rangeInputMax") %> <br/>
Outside temperature: <%= request.getParameter("rangeInputOut") %> <br/>
Socket Port: <%= request.getParameter("socketPort") %> <br/>

<% HvacClient client = new HvacClient();
  client.writeToSocketAtAPort(
          request.getParameter("rangeInputMin"),
          request.getParameter("rangeInputMax"),
          request.getParameter("rangeInputOut"),
          request.getParameter("socketPort"))
  ;%>

</body>
</html>
