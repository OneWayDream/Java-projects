<#import "spring.ftl" as spring/>

<#macro htmlHeader title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title}</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel=stylesheet href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.css" />
    <link rel=stylesheet href="https://unpkg.com/bootstrap-select@1.13.8/dist/css/bootstrap-select.css" />


    <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/main-style.css' />">

    <script type ="text/javascript" src="<@spring.url '/static/js/libs/jquery-3.6.0.js' />"></script>
    <script type ="text/javascript" src="<@spring.url '/static/js/change-background.js' />"></script>
    <script type ="text/javascript" src="<@spring.url '/static/js/button_background.js' />"></script>
    <script type ="text/javascript" src="<@spring.url '/static/js/set-image.js' />"></script>
</#macro>

<#macro htmlFooter>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="<@spring.url '/static/js/bootstrap/bootstrap.bundle.min.js' />"></script>
    <script src="https://unpkg.com/bootstrap-select@1.13.8/dist/js/bootstrap-select.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</#macro>

<#macro fuelOptions fuelsList fuelsLogosList>
    <option value="SELECT" data-content="<img src='<@spring.url '/static/images/fuels/SELECT.png' />' width='30' /> Empty"></option>
    <#list fuelsList as fuels>
        <option
            value="${fuelsLogosList.get(fuels ? index)}"
            data-content="<img src='<@spring.url '/static/images/fuels/${fuelsLogosList.get(fuels ? index)}.png' />' width='30' /> ${fuels}">
        </option>
    </#list>
</#macro>

<#macro minionOptions minionsList minionsLogosList>
    <#list 0..minionsList?size-1 as i>
        <option
            value=${minionsLogosList[i]}
            data-content="<img src='<@spring.url '/static/images/minions_head/${minionsLogosList[i]}_Head.png' />' width='30' /> ${minionsList[i]}">
        </option>
    </#list>
</#macro>

<#macro upgradeOptions upgradesList upgradesLogosList index>
    <option value="SELECT" data-content="<img src='<@spring.url '/static/images/upgrades/SELECT.png' />' width='30' /> Empty"></option>
    <#list upgradesList as upgrades>
        <option
                value="${upgradesLogosList.get(upgrades ? index)}"
                id='upgrade${index}${upgradesLogosList.get(upgrades ? index)}'
                data-content="<img src='<@spring.url '/static/images/upgrades/${upgradesLogosList.get(upgrades ? index)}.png' />' width='30' /> ${upgrades}">
        </option>
    </#list>
</#macro>

<#macro pageHeader login="">

    <header class="bg-dark" id="mainHeader">
        <div id="rootUrl" root-url="<@spring.url ''/>"></div>

        <button type="button" class="btn btn-primary" id="background-button-exchange">
            <img src="<@spring.url '/static/images/logos/exchange.png' />" width="25" height="25" alt="">
        </button>

        <div class="btn-group mr-2" role="group" aria-label="Second group">
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_1.jpg' />">B</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_2.png' />">a</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_3.png' />">c</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_4.png' />">k</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_5.jpg' />">g</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_6.png' />">r</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_7.jpg' />">o</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_8.jpg' />">u</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_9.png' />">n</button>
            <button type="button" class="btn btn-secondary button-panel"  data-image="<@spring.url '/static/images/background/background_10.jpg' />">d</button>
        </div>
<#--        <a href="<@spring.url '/main' />" class="site-name">-->
        <a href="${mvcUrl.fromMappingName('MC#getMainPage').build()}" class="site-name">
            <h2 class="text-light">Minions Valuation</h2>
        </a>
        <#if login="">
            <div class="btn-group" role="group" aria-label="Basic example">
<#--                <a class="btn btn-secondary" href="<@spring.url '/sign-up' />" role="button"><@spring.message 'page_header.sign_up_title'/></a>-->
                <a class="btn btn-secondary" href="${mvcUrl.fromMappingName('SC#getSignUpPage').build()}" role="button"><@spring.message 'page_header.sign_up_title'/></a>
<#--                <a class="btn btn-secondary" href="<@spring.url '/sign-in' />" role="button"><@spring.message 'page_header.sign_in_title'/></a>-->
                <a class="btn btn-secondary" href="${mvcUrl.fromMappingName('SC#getSignInPage').build()}" role="button"><@spring.message 'page_header.sign_in_title'/></a>
            </div>
        <#else>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${login}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
<#--                    <a class="dropdown-item" href="<@spring.url '/profile' />"><@spring.message 'page_header.account_title'/></a>-->
                    <a class="dropdown-item" href="${mvcUrl.fromMappingName('PC#getProfilePage').build()}"><@spring.message 'page_header.account_title'/></a>
<#--                    <a class="dropdown-item" href="<@spring.url '/logout' />"><@spring.message 'page_header.log_out_title'/></a>-->
                    <a class="dropdown-item" href="${mvcUrl.fromMappingName('SC#getLogoutPage').build()}"><@spring.message 'page_header.log_out_title'/></a>
                </div>
            </div>
        </#if>
        
        <div class="dropdown">
            <button class="btn btn-success dropdown-toggle" type="button" id="language_down_drop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <@spring.message 'page_header.language_button_title'/>
            </button>
            <div class="dropdown-menu" aria-labelledby="language_down_drop">
                <a class="dropdown-item" href="?lang=ru">
                    <p>
                        <img src="<@spring.url '/static/images/languages/ru-flag.png' />" width="20" height="20" alt="">
                        <@spring.message 'page_header.russian_lang_title'/>
                    </p>
                </a>
                <a class="dropdown-item" href="?lang=en">
                    <p>
                        <img src="<@spring.url '/static/images/languages/en-flag.png'/>" width="20" height="20" alt="">
                        <@spring.message 'page_header.english_lang_title'/>
                    </p>
                </a>
            </div>
        
    </header>
</#macro>