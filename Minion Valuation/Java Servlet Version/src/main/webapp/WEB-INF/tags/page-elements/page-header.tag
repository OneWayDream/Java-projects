<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="is_signed" required="true"%>
<%@attribute name="login" required="false" %>

<header class="bg-dark" id="mainHeader">

    <button type="button" class="btn btn-primary" id="background-button-exchange">
        <img src="${pageContext.request.contextPath}/static/images/logos/exchange.png" width="25" height="25">
    </button>

    <div class="btn-group mr-2" role="group" aria-label="Second group">
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_1.jpg">B</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_2.png">a</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_3.png">c</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_4.png">k</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_5.jpg">g</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_6.png">r</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_7.jpg">o</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_8.jpg">u</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_9.png">n</button>
        <button type="button" class="btn btn-secondary button-panel"  data-image="${pageContext.request.contextPath}/static/images/background/background_10.jpg">d</button>
    </div>
    <a href="${pageContext.request.contextPath}/main" class="site-name">
        <h2 class="text-light">Minions Valuation</h2>
    </a>
    <c:if test="${not empty is_signed}">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${login}
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Your account</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/log-out">Log out</a>
            </div>
        </div>
    </c:if>
    <c:if test="${empty is_signed}">
        <div class="btn-group sign-container" role="group" aria-label="Basic example">
            <a class="btn btn-info" href="${pageContext.request.contextPath}/sign-up" role="button">Sign Up</a>
            <a class="btn btn-info" href="${pageContext.request.contextPath}/sign-in" role="button">Sign In</a>
        </div>
    </c:if>
</header>