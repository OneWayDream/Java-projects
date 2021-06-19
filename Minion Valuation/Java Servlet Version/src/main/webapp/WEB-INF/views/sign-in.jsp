<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p:header title="Sign In"></p:header>

    <!-- Font Icon -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sign/material-design-iconic-font.min.css">
    <!-- Main css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sign/style.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sign/sign-forms.css">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/sign/sign-main.js"></script>
</head>
<body>
    <div id="select_values" user_background="${user_background}"></div>
    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>
    <div>
        <section>
            <div class="container bg-dark">
                <div class="signin-content">
                    <div class="signin-form">
                        <h3 class="form-title text-light">Sign in</h3>
                        <c:if test="${not empty message}">
                            <h4 class="text-danger">${message}</h4>
                        </c:if>
                        <form method="POST" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="login" id="login" placeholder="Your Login" value="${user_login}" required minlength="3" maxlength="30"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="your_pass" id="your_pass" placeholder="Password" required minlength="8" maxlength="40"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term text-light"><span><span></span></span>Remember me</label>
                            </div>
                            <input type="hidden" name="user_background" id="user_background" value="${user_background}">
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                            </div>
                        </form>
                        <a href="${pageContext.request.contextPath}/sign-up" class="signup-image-link text-light">Create an account</a>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/sign/jquery-ui.min.js"></script>
<p:footer></p:footer>