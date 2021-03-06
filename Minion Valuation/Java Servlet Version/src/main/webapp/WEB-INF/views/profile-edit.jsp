<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p:header title="${login}-edit"></p:header>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sign/sign-forms.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/profile/profile.css">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/profile-edit/profile-edit-main.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/profile-edit/set-user-gender.js"></script>

</head>
<body>
    <div id="select_values" user_background="${user_background}" gender="${gender}"></div>
    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>
    <div class="container emp-profile bg-dark">
        <form method="post">
            <input type="hidden" name="form" value="user_data">
            <input type="hidden" name="user_background" id="user_background" value="${user_background}">
            <div class="row">
                <div class="col-md-4">
                    <div class="profile-img">
                        <img src="${pageContext.request.contextPath}/static/images/users-images/${image}.png" alt=""/>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="row">
                        <h2 class="text-light">Change your userdata.</h2>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>${message}</label>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Login</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_login" id="new_login" placeholder="Your Login" value="${login}" required minlength="3" maxlength="30">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Firstname</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_first_name" id="new_first_name" placeholder="Your First Name" value="${first_name}" minlength="2" maxlength="30">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Minecraft nickname</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_nickname" id="new_nickname" placeholder="Your Minecraft Nickname" value="${nickname}" minlength="4" maxlength="30">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Email</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="email" name="new_email" id="new_email" placeholder="Your Email (example - example@exam.ple)" value="${email}" required pattern="[\w\-]+@([\w\-]+\.)+([\w\-]+)$" maxlength="30"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Gender</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <select name="new_gender" id="new_gender">
                                <option name="Male" id="Male" value="Male">Male</option>
                                <option name="Female" id="Female" value="Female">Female</option>
                                <option name="Attack_Helicopter" id="Attack_Helicopter" value="Attack Helicopter">Attack Helicopter</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Country</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_country" id="new_country" placeholder="Your Country" value="${country}" minlength="1" maxlength="30">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>VK</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_vk" id="new_vk" placeholder="Your VK" value="${vk}" pattern="vk.com/[\w]+" maxlength="30"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Facebook</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="text" name="new_facebook" id="new_facebook" placeholder="Your Facebook" value="${facebook}" pattern="facebook.com/[\w\]+" maxlength="30"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <input type="submit" class="btn btn-secondary" role="button" value="Save changes">
                </div>
            </div>
        </form>
        <form method="post">
            <input type="hidden" name="form" value="change_password">
            <input type="hidden" name="user_background" id="user_background" value="${user_background}">
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <h2 class="text-light">Change your password.</h2>
                    </div>
                    <c:if test="${not empty password_message}">
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>${password_message}</label>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Your current password</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="password" name="current_password" id="current_password" placeholder="Your Current Password" value="" required minlength="8" maxlength="40">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Your new password</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="password" name="new_password" id="new_password" placeholder="New password" value="" required minlength="8" maxlength="40">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Repeat your new password</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="password" name="re_new_password" id="re_new_password" placeholder="Repeat new password" value="" required minlength="8" maxlength="40">
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" class="btn btn-secondary" role="button" value="Change your password">
                    </div>
                </div>
            </div>
        </form>
        <form method="post">
            <input type="hidden" name="form" value="delete_account">
            <input type="hidden" name="user_background" id="user_background" value="${user_background}">
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <h2 class="text-light">Delete your account.</h2>
                    </div>
                    <c:if test="${not empty delete_message}">
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>${delete_message}</label>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Your password</label>
                        </div>
                        <div class="col-md-6 text-light">
                            <input type="password" name="delete_password" id="delete_password" placeholder="Your Current Password" value="" required minlength="8" maxlength="40">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-light">
                            <label>Access to delete</label>
                            <input type="checkbox"  name="delete_access" id="delete_access" required>
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" class="btn btn-secondary" role="button" value="Delete account">
                    </div>
                </div>
            </div>
        </form>
    </div>
<p:footer></p:footer>