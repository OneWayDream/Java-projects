<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="fuelsList" required="true" type="java.util.List" %>
<%@ attribute name="fuelsLogosList" required="true" type="java.util.List" %>

<option value="SELECT" data-content="<img src='${pageContext.request.contextPath}/static/images/fuels/SELECT.png' width='30' /> Empty"></option>
<c:forEach var="fuel" items="${fuelsList}" begin="0" varStatus="counter">
    <option value="${fuelsLogosList.get(counter.index)}" data-content="<img src='${pageContext.request.contextPath}/static/images/fuels/${fuelsLogosList.get(counter.index)}.png' width='30' /> ${fuel}"></option>
</c:forEach>