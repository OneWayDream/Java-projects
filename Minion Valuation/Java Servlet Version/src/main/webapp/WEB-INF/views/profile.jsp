<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p:header title="${login}"></p:header>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/profile/profile.css">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/profile/profile-main.js"></script>

</head>
<body>
    <div id="select_values" user_background="${user_background}"></div>
    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>
    <div class="container emp-profile bg-dark">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="${pageContext.request.contextPath}/static/images/users-images/${image}.png" alt=""/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h5 class="text-light">
                        ${login}
                    </h5>
                    <h6 class="${text_color}">
                        ${role}
                    </h6>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Main info</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">About</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/profile-edit" role="button">Edit profile</a>
                <c:if test="${not empty message}">
                    <p class="text-success">${message}</p>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Firstname</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${first_name}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Minecraft nickname</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${nickname}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Email</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${email}</p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Gender</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${gender}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Country</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${country}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>VK</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${vk}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label>Facebook</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <p>${facebook}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <p:footer></p:footer>
