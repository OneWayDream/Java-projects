<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Sign Up"/>
        <!-- Font Icon -->
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/sign/material-design-iconic-font.min.css' />">
        <!-- Main css -->
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/sign/style.css' />">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/sign/sign-forms.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/sign/sign-main.js' />"></script>
        <style>
            .error{
                color: white;
            }
        </style>
    </head>
    <body>
        <#if login??>
            <@m.pageHeader login="${login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>
        <div >
            <section >
                <div class="container bg-dark">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title text-light"><@spring.message "sign_up_page.sign_up_form.title"/></h2>
                            <#if message??>
                                <h3 class="text-light">${message}</h3>
                            </#if>
                            <#if spring_message??>
                                <h3 class="text-light"><@spring.message '${spring_message}'/></h3>
                            </#if>
                            <@spring.bind "signUpForm"/>
                            <form method="POST" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <#assign loginPlaceholder><@spring.message 'sign_up_page.sign_up_form.login_placeholder'/></#assign>
                                    <@spring.formInput "signUpForm.login" "id='login' placeholder='" + loginPlaceholder + "' required minlength='3' maxlength='30'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <#assign emailPlaceholder><@spring.message 'sign_up_page.sign_up_form.email_placeholder'/></#assign>
                                    <@spring.formInput "signUpForm.email" "id='email' placeholder='" + emailPlaceholder + "' required minlength='5'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <#assign passwordPlaceholder><@spring.message 'sign_up_page.sign_up_form.password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "signUpForm.password" "id='password' placeholder='" + passwordPlaceholder + "' required minlength='8' maxlength='40'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                                <div class="form-group">
                                    <label for="repeated-password"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <#assign repeatedPasswordPlaceholder><@spring.message 'sign_up_page.sign_up_form.repeated_password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "signUpForm.repeatedPassword" "id='repeated-password' placeholder='" + repeatedPasswordPlaceholder + "' required minlength='8' maxlength='40'"/>
                                </div>
                                <div class="form-group">
<#--                                    <@spring.bind 'signUpForm.userAccess'/>-->
<#--                                    <input type="checkbox" id="user-access" class="agree-term"/>-->
                                    <@spring.formCheckbox "signUpForm.userAccess" "class='agree-term' required"/>
                                    <label for="userAccess" class="label-agree-term text-light"><span><span></span></span><@spring.message "sign_up_page.sign_up_form.agree_term_span"/><a href="#" class="term-service text-light"><@spring.message "sign_up_page.sign_up_form.agree_term_href"/></a></label>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error" />
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="<@spring.message "sign_up_page.sign_up_form.sign_up_button"/>"/>
                                </div>
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                            </form>
                            <a href="${mvcUrl.fromMappingName('SC#getSignInPage').build()}" class="signup-image-link text-light"><@spring.message "sign_up_page.sign_up_form.link_to_sign_in"/></a>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <!-- JS -->
        <script type ="text/javascript" src="<@spring.url '/static/js/sign/jquery-ui.min.js' />"></script>
        <@m.htmlFooter/>
    </body>
</html>