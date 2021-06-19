<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Success"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/success/success-styles.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/success/success-main.js' />"></script>
    </head>
    <body>
        <#if login??>
            <@m.pageHeader login="${login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>
        <div class="container" id="mainContainer">
            <div class="bg-dark info">
                <h2 class="text-success"><@spring.message 'success_page.thx_message'/></h2>
                <h3 class="text-light"><@spring.message 'success_page.new_account_info'/></h3>
                <p>
                    <a href="${mvcUrl.fromMappingName('MC#getMainPage').build()}" class="text-light"><@spring.message "success_page.link_to_sign_in"/></a>
                </p>
                <p>
                    <a href="${mvcUrl.fromMappingName('SC#getSignInPage').build()}" class="text-light"><@spring.message "success_page.link_to_main_page"/></a>
                </p>
            </div>
        </div>
        <@m.htmlFooter/>
    </body>
</html>