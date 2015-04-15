<%--
  Created by IntelliJ IDEA.
  User: Zoltan_Beke
  Date: 15/04/15
  Time: 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>HVAC Clinet</title>
</head>
<body>
<h1>Oh boy - start your Server! ;)</h1>
<img src="http://myappliancesservices.com/files/data1/images/4.jpg"  height="240" width="600"/>
<form action="data_sent.jsp">
  <table>
    <tr>
      <td>Minimum temperature:</td>
      <td>
        <input type="range" id="rangeInputMin" size="10" name="rangeInputMin" min="55" max="95" value="65" oninput="amountMin.value=rangeInputMin.value"/>
        <output name="amountMin" for="rangeInputMin">65</output>
      </td>
    </tr>
    <tr>
      <td>Maximum temperature:</td>
      <td>
        <input type="range" id="rangeInputMax" size="10" name="rangeInputMax" min="55" max="95" value="75" oninput="amountMax.value=rangeInputMax.value"/>
        <output name="amountMax" for="rangeInputMax">75</output>
      </td>
    </tr>
    <tr>
      <td>Outside temperature:</td>
      <td>
        <input type="range" id="rangeInputOut" size="10" name="rangeInputOut" min="55" max="95" value="75" oninput="amountOut.value=rangeInputOut.value"/>
        <output name="amountOut" for="rangeInputOut">75</output>
      </td>
    </tr>
  </table>

  <input type="submit" value="Submit">

</form>

</body>
</html>
