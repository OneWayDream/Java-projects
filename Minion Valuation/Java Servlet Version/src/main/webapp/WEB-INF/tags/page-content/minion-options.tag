<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="minionsList" required="true" type="java.util.List" %>
<%@ attribute name="minionsLogosList" required="true" type="java.util.List" %>

<c:forEach var="minion" items="${minionsList}" begin="0" varStatus="counter">
    <option value="${minionsLogosList.get(counter.index)}" data-content="<img src='${pageContext.request.contextPath}/static/images/minions_head/${minionsLogosList.get(counter.index)}_Head.png' width='30' /> ${minion}"></option>
</c:forEach>