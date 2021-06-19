<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Minion Valuation"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/main-page/main-page-styles.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/mainpage.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/minion-image.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/fuel-image.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/upgrade1-image.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/upgrade2-image.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/tooltip.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/main-page/calculate-ajax.js' />"></script>
    </head>
    <body>
        <div id="current_path" current_path="<@spring.url ''/>"></div>

        <#if user ??>
            <@m.pageHeader login="${user.login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>

        <div class="container" id="mainContainer">
            <#if dataDownloadError??>
                <div class="container text-warning">
                    <h2><@spring.message 'errors.main.bad_data_connection'/></h2>
                </div>
            </#if>

            <#if top3BazaarInfo ??>

                <div class="container top-3 bg-dark">
                    <div class="container top-3-header">
                        <header class="text-light" >
                            <h2 class="text-black-100 top-3-header-span">
                                <@spring.message 'main.top_3_bazaar_title'/>
                                <div class="top-3-tooltip" data-toggle="tooltip" data-placement="right" title="<@spring.message 'main.top_3_bazaar_tooltip'/>">
                                    <img src="<@spring.url '/static/images/logos/warning_icon.png' />" width="35" height="35">
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
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3BazaarInfo[0].imageName}_Pose.png' />" alt="First slide" height="200">
                                    <h5 class="text-light">${top3BazaarInfo[0].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="first-place"><@spring.message 'main.first_top_place'/></h3>
                                    <h4 class="text-success">${top3BazaarInfo[0].profit} coins / day</h4>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="container top-3-image">
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3BazaarInfo[1].imageName}_Pose.png' />" alt="Second slide" height="200">
                                    <h5 class="text-light">${top3BazaarInfo[1].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="second-place"><@spring.message 'main.second_top_place'/></h3>
                                    <h4 class="text-success">${top3BazaarInfo[1].profit} coins / day</h4>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="container top-3-image">
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3BazaarInfo[2].imageName}_Pose.png' />" alt="Third slide" height="200">
                                    <h5 class="text-light">${top3BazaarInfo[2].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="third-place"><@spring.message 'main.third_top_place'/></h3>
                                    <h4 class="text-success">${top3BazaarInfo[2].profit} coins / day</h4>
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
            <#else>
                <div class="container top-3 bg-dark">
                    <p class="text-light"><@spring.message 'errors.main.no_top_bazaar_data'/></p>
                </div>
            </#if>

            <#if (top3NpsInfo ??)>
                <div class="container top-3 bg-dark">
                    <div class="container top-3-header">
                        <header class="text-light" >
                            <h2 class="text-black-100">
                                <@spring.message 'main.top_3_nps_title'/>
                                <div class="top-3-tooltip" data-toggle="tooltip" data-placement="right" title="<@spring.message 'main.top_3_nps_tooltip'/>">
                                    <img src="<@spring.url '/static/images/logos/warning_icon.png' />" width="35" height="35">
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
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3NpsInfo[0].imageName}_Pose.png' />" alt="First slide" height="200">
                                    <h5 class="text-light">${top3NpsInfo[0].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="first-place"><@spring.message 'main.first_top_place'/></h3>
                                    <h4 class="text-success">${top3NpsInfo[0].profit} coins / day</h4>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="container top-3-image">
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3NpsInfo[1].imageName}_Pose.png' />" alt="Second slide" height="200">
                                    <h5 class="text-light">${top3NpsInfo[1].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="second-place"><@spring.message 'main.second_top_place'/></h3>
                                    <h4 class="text-success">${top3NpsInfo[1].profit} coins / day</h4>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="container top-3-image">
                                    <img class="d-block w-15" src="<@spring.url '/static/images/minions_pose/${top3NpsInfo[2].imageName}_Pose.png' />" alt="Third slide"  height="200">
                                    <h5 class="text-light">${top3NpsInfo[2].minionName}</h5>
                                </div>
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 class="third-place"><@spring.message 'main.third_top_place'/></h3>
                                    <h4 class="text-success">${top3NpsInfo[2].profit} coins / day</h4>
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
            <#else>
                <div class="container top-3 bg-dark">
                    <p class="text-light"><@spring.message 'errors.main.no_top_nps_data'/></p>
                </div>
            </#if>

            <#if (minionsDataList ??) && (fuelsDataList ??) && (upgradesDataList ??)>
                <div class="container calculator bg-dark" id="calculator">
                    <div class="container calculator-header">
                        <header class="text-light" >
                            <h2 class="text-black-100 calculator-header-span">
                                <@spring.message 'main.calculator_title'/>
                            </h2>
                        </header>
                    </div>
                    <div class="container calculator-body">
                        <form action="" method="post" onsubmit="return false;">
                            <div class="container container-minion-image">
                                <img class="minion-image" src="<@spring.url '/static/images/minions_head/SELECT.png' />" width="216" >
                            </div>
                            <select title="<@spring.message 'main.calculator.minion_select_title'/>" id="minion" name="minion" class="selectpicker minion-choose">
                                <#list minionsDataList as minionData>
                                    <option
                                            value=${minionData.optionValue}
                                            data-content="<img src='<@spring.url '/static/images/minions_head/${minionData.imageName}_Head.png' />' width='30' /> ${minionData.name}">
                                    </option>
                                </#list>
                            </select>
                            <select title="<@spring.message 'main.calculator.tier_select_title'/>" id="tier" name="tier">
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
<#--                            <a class="text-light">Tier</a>-->
                            <div class="container container-minion-upgrades">
                                <div class="container fuels">
                                    <div class="container container-fuel-image">
                                        <img class="fuel-image" src="<@spring.url '/static/images/fuels/SELECT.png' />" width="144" >
                                    </div>
                                    <select title="<@spring.message 'main.calculator.fuel_select_title'/>" id="fuel" name="fuel" class="selectpicker fuel-choose" size="5">
                                        <option value="SELECT" data-content="<img src='<@spring.url '/static/images/fuels/SELECT.png' />' width='30' /> Empty"></option>
                                        <#list fuelsDataList as fuelData>
                                            <option
                                                    value="${fuelData.optionValue}"
                                                    data-content="<img src='<@spring.url '/static/images/fuels/${fuelData.imageName}.png' />' width='30' /> ${fuelData.name}">
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="container upgrades1">
                                    <div class="container container-upgrade1-image">
                                        <img class="upgrade1-image" src="<@spring.url '/static/images/upgrades/SELECT.png' />" width="144" >
                                    </div>
                                    <select title="<@spring.message 'main.calculator.first_upgrade_select_title'/>" id="upgrade1" name="upgrade1" class="selectpicker upgrade1-choose">
                                        <option value="SELECT" data-content="<img src='<@spring.url '/static/images/upgrades/SELECT.png' />' width='30' /> Empty"></option>
                                        <#list upgradesDataList as upgradeData>
                                            <option
                                                    value="${upgradeData.optionValue}"
                                                    id='upgrade1${upgradeData.optionValue}'
                                                    data-content="<img src='<@spring.url '/static/images/upgrades/${upgradeData.imageName}.png' />' width='30' /> ${upgradeData.name}">
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="container upgrades2">
                                    <div class="container container-upgrade2-image">
                                        <img class="upgrade2-image" src="<@spring.url '/static/images/upgrades/SELECT.png' />" width="144" >
                                    </div>
                                    <select title="<@spring.message 'main.calculator.second_upgrade_select_title'/>"  id="upgrade2" name="upgrade2" class="selectpicker upgrade2-choose">
                                        <option value="SELECT" data-content="<img src='<@spring.url '/static/images/upgrades/SELECT.png' />' width='30' /> Empty"></option>
                                        <#list upgradesDataList as upgradeData>
                                            <option
                                                    value="${upgradeData.optionValue}"
                                                    id='upgrade2${upgradeData.optionValue}'
                                                    data-content="<img src='<@spring.url '/static/images/upgrades/${upgradeData.imageName}.png' />' width='30' /> ${upgradeData.name}">
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <input type="submit" value="<@spring.message 'main.calculator.submit_button'/>" id="calculate" class="btn btn-primary">
                        </form>
                    </div>
                    <div class="container" id="result">
                        <h2 class="text-danger" id="errorPanel"></h2>
                        <h2 class="text-warning" id="warningPanel"></h2>
                        <h2 class="text-success" id="resultPanel"></h2>
                    </div>
                </div>
            <#else>
                <div class="container calculator bg-dark">
                    <p class="text-light"><@spring.message 'errors.main.no_calculator_data'/></p>
                </div>
            </#if>

        </div>
        <@m.htmlFooter/>
    </body>
</html>