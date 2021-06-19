<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p:header title="Success"></p:header>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/success/success-styles.css">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/success/success-main.js"></script>
</head>
<body>
    <div id="select_values" user_background="${user_background}"></div>
    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>
    <div class="container" id="mainContainer">
        <div class="bg-dark info">
            <h2 class="text-success">Thank you for registering</h2>
            <h3 class="text-light">Your account has been successfully registered on our website</h3>
            <p>
                <a href="${pageContext.request.contextPath}/sign-in" class="text-light">Login to your account</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/main" class="text-light">Go to home page</a>
            </p>
        </div>
    </div>
    <p:footer></p:footer>
