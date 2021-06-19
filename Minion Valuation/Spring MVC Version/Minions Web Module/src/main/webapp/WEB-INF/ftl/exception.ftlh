<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Exception"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/exception/exception-styles.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/exception/exception-main.js' />"></script>
    </head>
    <body>
        <#if user ?? && user.login??>
            <@m.pageHeader login="${user.login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>
        <div class="container" id="mainContainer">
            <div class="bg-dark info">
                <#if message??>
                    <h3 class="text-light">${message}</h3>
                </#if>
            </div>
            <div class="bg-dark info">
<#--                <a href="<@spring.url '/main' />">Return to the main page.</a>-->
                <a href="${mvcUrl.fromMappingName('MC#getMainPage').build()}">Return to the main page.</a>
            </div>
        </div>
        <@m.htmlFooter/>
    </body>
</html>