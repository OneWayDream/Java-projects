<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Sign In"/>
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
        <div>
            <section>
                <div class="container bg-dark">
                    <div class="signin-content">
                        <div class="signin-form">
                            <h3 class="form-title text-light"><@spring.message 'sign_in_page.sign_in_form.title'/></h3>

                            <#if message??>
                                <p class="text-light"><@spring.message '${message}'/></p>
                            </#if>
                            <#if warning??>
                                <p class="text-success"><@spring.message '${warning}'/></p>
                            </#if>
                            <#if error??>
                                <p class="text-danger"><@spring.message '${error}'/></p>
                            </#if>

                            <@spring.bind "signInForm"/>
                            <form method="POST" class="register-form" id="login-form">
                                <div class="form-group">
                                    <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
<#--                                    <input type="text" name="login" id="login" placeholder="<@spring.message 'sign_in_page.sign_in_form.login_placeholder'/>"-->
<#--                                           <#if user_login??>value="${user_login}"</#if> required minlength="3" maxlength="30"/>-->
                                    <#assign loginPlaceholder><@spring.message 'sign_in_page.sign_in_form.login_placeholder'/></#assign>
                                    <@spring.formInput "signInForm.login" "id='login' placeholder='" + loginPlaceholder + "' required minlength='3' maxlength='30'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error" />
                                </div>
                                <div class="form-group">
                                    <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
<#--                                    <input type="password" name="yourPassword" id="your_pass" placeholder="<@spring.message 'sign_in_page.sign_in_form.password_placeholder'/>" required minlength="8" maxlength="40"/>-->
                                    <#assign passwordPlaceholder><@spring.message 'sign_in_page.sign_in_form.password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "signInForm.password" "id='password' placeholder='" + passwordPlaceholder + "' required minlength='8' maxlength='40'"/>
<#--                                    <@spring.showErrors "<br>" "error" />-->
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error" />
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" id="remember-me" name="remember-me" class="agree-term">
<#--                                    <@spring.formCheckbox "signInForm.rememberMe" "id='remember-me'"/>-->
                                    <@spring.showErrors "<br>" "error" />
                                    <label for="remember-me" class="label-agree-term text-light"><span><span></span></span><@spring.message 'sign_in_page.sign_in_form.remember_me_checkbox'/></label>
                                </div>
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="<@spring.message 'sign_in_page.sign_in_form.log_in_button'/>"/>
                                </div>
                            </form>
                            <div class="references">
                                <a href="${mvcUrl.fromMappingName('SC#getGitOauthPage').build()}">
                                    <img src="<@spring.url '/static/images/logos/github.svg'/>" width="50" height="50" alt="<@spring.message 'sign_in_page.github_alt'/>">
                                </a>
                            </div>
                            <a href="${mvcUrl.fromMappingName('SC#getSignUpPage').build()}" class="signup-image-link text-light"><@spring.message 'sign_in_page.sign_in_form.href_to_sign_up'/></a>
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