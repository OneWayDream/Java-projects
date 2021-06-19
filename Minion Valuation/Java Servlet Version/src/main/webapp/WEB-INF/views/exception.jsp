<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p:header title="Exception"></p:header>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/exception/exception-styles.css">
        <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/exception/exception-main.js"></script>

    </head>


    <body>
    <div id="select_values" user_background="${user_background}"></div>
    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>
    <div class="container" id="mainContainer">
        <div class="bg-dark info">
            <c:if test="${not empty message}">
                <h3 class="text-light">${message}</h3>
            </c:if>
        </div>
        <div class="bg-dark info">
            <a href="${pageContext.request.contextPath}/main">Return to the main page.</a>
        </div>
    </div>

<p:footer></p:footer>