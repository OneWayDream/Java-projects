<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="upgradesList" required="true" type="java.util.List" %>
<%@ attribute name="upgradesLogosList" required="true" type="java.util.List" %>
<%@ attribute name="index" required="true" type="java.lang.Integer" %>

<option value="SELECT" data-content="<img src='${pageContext.request.contextPath}/static/images/upgrades/SELECT.png' width='30' /> Empty"></option>
<c:forEach var="upgrade" items="${upgradesList}" begin="0" varStatus="counter">
    <option
            value="${upgradesLogosList.get(counter.index)}"
            id='upgrade${index}${upgradesLogosList.get(counter.index)}'
            data-content="<img src='${pageContext.request.contextPath}/static/images/upgrades/${upgradesLogosList.get(counter.index)}.png' width='30' /> ${upgrade}">
    </option>
</c:forEach>