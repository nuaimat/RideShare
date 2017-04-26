<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 4/26/2017
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="templates/header_template.jsp"/>
<h2>Hello Weather!</h2>
<input type="hidden" name="city" value="${user.city}"></input>
<div id="current weather"></div>
<div id="forcast"></div>
<jsp:include page="templates/footer_template.jsp"/>