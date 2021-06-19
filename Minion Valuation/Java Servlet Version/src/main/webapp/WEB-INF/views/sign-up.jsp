<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p:header title="Sign Up"></p:header>

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
    <div >
        <section >
            <div class="container bg-dark">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title text-light">Sign up</h2>
                        <c:if test="${not empty message}">
                            <h3 class="text-danger">${message}</h3>
                        </c:if>
                        <form method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="login" id="login" placeholder="Your Login" value="${user_login}" required minlength="3" maxlength="30"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email (example - example@exam.ple)" value="${user_email}" required pattern="[\w\-]+@([\w\-]+\.)+([\w\-]+)$" maxlength="30"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" required minlength="8" maxlength="40"/>
                            </div>
                            <div class="form-group">
                                <label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required minlength="8" maxlength="40"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" required/>
                                <label for="agree-term" class="label-agree-term text-light"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <input type="hidden" name="user_background" id="user_background" value="${user_background}">
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                            </div>
                        </form>
                        <a href="${pageContext.request.contextPath}/sign-in" class="signup-image-link text-light">I am already member</a>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/sign/jquery-ui.min.js"></script>
<p:footer></p:footer>