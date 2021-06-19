<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/page-content" %>
<%@taglib prefix="e" tagdir="/WEB-INF/tags/page-elements"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p:header title="Minion Valuatuin"></p:header>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main-page/main-page-styles.css">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/mainpage.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/minion-image.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/fuel-image.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/upgrade1-image.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/upgrade2-image.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/set-values-mainpage.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/page-move.js"></script>
    <script type ="text/javascript" src="${pageContext.request.contextPath}/static/js/main-page/tooltip.js"></script>

</head>
<body >
    <div id="current_path" current_path="${pageContext.request.contextPath}"></div>
    <div id="select_values" minion_value="${minionValue}"
         fuel_value="${fuelValue}"
         upgrade1_value="${upgrade1Value}"
         upgrade2_value="${upgrade2Value}"
         tier_value="${tierValue}"
         is_req="${isReq}"
         user_background="${user_background}"></div>

    <e:page-header is_signed="${is_signed}" login="${login}"></e:page-header>

    <div class="container" id="mainContainer">
        <c:if test="${not empty dataDownloadError}">
            <div class="container text-warning">
                <h2>It is not possible to update the price lists at this time, so the site data may be inaccurate.</h2>
            </div>
        </c:if>
        <div class="container top-3 bg-dark">
            <div class="container top-3-header">
                <header class="text-light" >
                    <h2 class="text-black-100 top-3-header-span">
                        Top 3 minions for Bazaar Sell
                        <div class="top-3-tooltip" data-toggle="tooltip" data-placement="right" title="Tier XI minions without fuel are considered">
                            <img src="${pageContext.request.contextPath}/static/images/logos/warning_icon.png" width="35" height="35">
                        </div>
                    </h2>
                </header>
            </div>
            <div id="carouselExampleIndicators" class="carousel slide" data-interval="false">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${bazaar1image}_Pose.png" alt="First slide" height="200">
                            <h5 class="text-light">${top1bazaar}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="first-place">First place</h3>
                            <h4 class="text-success">${top1bazaarProfit} coins / day</h4>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${bazaar2image}_Pose.png" alt="Second slide" height="200">
                            <h5 class="text-light">${top2bazaar}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="second-place">Twice place</h3>
                            <h4 class="text-success">${top2bazaarProfit} coins / day</h4>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${bazaar3image}_Pose.png" alt="Third slide" height="200">
                            <h5 class="text-light">${top3bazaar}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="third-place">Third place</h3>
                            <h4 class="text-success">${top3bazaarProfit} coins / day</h4>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="container top-3 bg-dark">
            <div class="container top-3-header">
                <header class="text-light" >
                    <h2 class="text-black-100">
                        Top 3 minions for NPS Sell
                        <div class="top-3-tooltip" data-toggle="tooltip" data-placement="right" title="Tier XI minions without fuel are considered">
                            <img src="${pageContext.request.contextPath}/static/images/logos/warning_icon.png" width="35" height="35">
                        </div>
                    </h2>
                </header>
            </div>
            <div id="carouselExampleIndicators2" class="carousel slide" data-interval="false">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators2" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators2" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators2" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${nps1image}_Pose.png" alt="First slide" height="200">
                            <h5 class="text-light">${top1nps}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="first-place">First place</h3>
                            <h4 class="text-success">${top1npsProfit} coins / day</h4>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${nps2image}_Pose.png" alt="Second slide" height="200">
                            <h5 class="text-light">${top2nps}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="second-place">Twice place</h3>
                            <h4 class="text-success">${top2npsProfit} coins / day</h4>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container top-3-image">
                            <img class="d-block w-15" src="${pageContext.request.contextPath}/static/images/minions_pose/${nps3image}_Pose.png" alt="Third slide"  height="200">
                            <h5 class="text-light">${top3nps}</h5>
                        </div>
                        <div class="carousel-caption d-none d-md-block">
                            <h3 class="third-place">Third place</h3>
                            <h4 class="text-success">${top3npsProfit} coins / day</h4>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators2" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators2" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="container calculator bg-dark" id="calculator">
            <div class="container calculator-header">
                <header class="text-light" >
                    <h2 class="text-black-100 calculator-header-span">
                        Calculation minion profit
                    </h2>
                </header>
            </div>
            <div class="container calculator-body">
                <form action="" method="post">
                    <div class="container container-minion-image">
                        <img class="minion-image" src="${pageContext.request.contextPath}/static/images/minions_head/SELECT.png" width="216" >
                    </div>
                        <select title="Select minion" id="minion" name="minion" class="selectpicker minion-choose">
                            <p:minion-options minionsList="${minionsList}" minionsLogosList="${minionsLogosList}"></p:minion-options>
                        </select>
                        <select title="Select minion tier" id="tier" name="tier">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                        </select>
                        <a class="text-light">Tier</a>
                    <div class="container container-minion-upgrades">
                        <div class="container fuels">
                            <div class="container container-fuel-image">
                                <img class="fuel-image" src="${pageContext.request.contextPath}/static/images/fuels/SELECT.png" width="144" >
                            </div>
                            <select title="Select fuel" id="fuel" name="fuel" class="selectpicker fuel-choose" size="5">
                                <p:fuel-options fuelsList="${fuelsList}" fuelsLogosList="${fuelsLogosList}"></p:fuel-options>
                            </select>
                        </div>
                        <div class="container upgrades1">
                            <div class="container container-upgrade1-image">
                                <img class="upgrade1-image" src="${pageContext.request.contextPath}/static/images/upgrades/SELECT.png" width="144" >
                            </div>
                            <select title="Select first upgrade" id="upgrade1" name="upgrade1" class="selectpicker upgrade1-choose">
                                <p:upgrade-options upgradesList="${upgradesList}" upgradesLogosList="${upgradesLogosList}" index="1"></p:upgrade-options>
                            </select>
                        </div>
                        <div class="container upgrades2">
                            <div class="container container-upgrade2-image">
                                <img class="upgrade2-image" src="${pageContext.request.contextPath}/static/images/upgrades/SELECT.png" width="144" >
                            </div>
                            <select title="Select second upgrade"  id="upgrade2" name="upgrade2" class="selectpicker upgrade2-choose">
                                <p:upgrade-options upgradesList="${upgradesList}" upgradesLogosList="${upgradesLogosList}" index="2"></p:upgrade-options>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="reboot_minion" value="${minionValue}">
                    <input type="hidden" name="reboot_fuel" value="${fuelValue}">
                    <input type="hidden" name="reboot_upgrade1" value="${upgrade1Value}">
                    <input type="hidden" name="reboot_upgrade2" value="${upgrade2Value}">
                    <input type="hidden" name="user_background" id="user_background" value="${user_background}">
                    <input type="submit" value="Calculate" class="btn btn-primary">
                </form>
            </div>
            <div class="container" id="result">
                <c:if test="${not empty errorMessage}">
                    <h2 class="text-danger">Error: ${errorMessage}</h2>
                </c:if>
                <c:if test="${not empty warningMessage}">
                    <h2 class="text-warning">Warning: ${warningMessage}</h2>
                </c:if>
                <c:if test="${not empty calculateResult}">
                    <h2 class="text-success">Result: ${calculateResult} coins per 24 hours.</h2>
                </c:if>
            </div>
        </div>
    </div>
<p:footer></p:footer>