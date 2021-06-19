<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="${user.login}"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/sign/sign-forms.css' />">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/profile/profile.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/profile-edit/profile-edit-main.js' />"></script>
        <script type ="text/javascript" src="<@spring.url '/static/js/profile-edit/set-user-gender.js' />"></script>
        <style>
            .error{
                color: white;
            }
        </style>
    </head>
    <body>
        <#if user.login??>
            <@m.pageHeader login="${user.login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>
        <div id="user_data" gender="${user.gender}"></div>
        <div class="container emp-profile bg-dark">

            <@spring.bind "profileEditForm"/>
            <form method="post" action="profile-data-edit">
                <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </#if>
                <div class="row">
                    <div class="col-md-4">
                        <div>
                            <img src="<@spring.url '/static/images/users-images/${user.image}.png' />" width="300" height="300"/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="row">
                            <h2 class="text-light"><@spring.message 'profile_edit_page.data_edit_label'/></h2>
                        </div>
                        <#if message??>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label>${data_message}</label>
                                </div>
                            </div>
                        </#if>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.login'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                    ${user.login} <@spring.message 'profile_edit_page.login_note'/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.first_name'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign firstNamePlaceholder><@spring.message 'profile_edit_page.first_name_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.firstName" "id='first_name' placeholder='" + firstNamePlaceholder + "' minlength='2' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.minecraft_nickname'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign nicknamePlaceholder><@spring.message 'profile_edit_page.minecraft_nickname_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.minecraftNickname" "id='minecraft_nickname' placeholder='" + nicknamePlaceholder + "' minlength='4' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.email'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign emailPlaceholder><@spring.message 'profile_edit_page.email_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.email" "id='email' placeholder='" + emailPlaceholder + "' required pattern='[\\w\\-]+@([\\w\\-]+\\.)+([\\w\\-]+)$' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.gender'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <@spring.bind 'profileEditForm.gender'/>
                                <select name="gender" id="gender">
                                    <#list genders as g>
                                        <option name="${g.gender}" id="${g.gender}" value="${g.gender}"><@spring.message '${g.message}'/></option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.country'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign countryPlaceholder><@spring.message 'profile_edit_page.country_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.country" "id='country' placeholder='" + countryPlaceholder + "' minlength='1' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.vk'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign vkPlaceholder><@spring.message 'profile_edit_page.vk_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.vk" "id='vk' placeholder='" + vkPlaceholder + "' pattern='vk.com/[\\w]+' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.facebook'/></label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign facebookPlaceholder><@spring.message 'profile_edit_page.facebook_placeholder'/></#assign>
                                <@spring.formInput "profileEditForm.facebook" "id='facebook' placeholder='" + facebookPlaceholder + "' pattern='facebook.com/[\\w\\]+' maxlength='30'"/>
                            </div>
                            <div class="form-group">
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-2">
                        <input type="submit" class="btn btn-secondary" role="button" value="<@spring.message 'profile+edit_page.data_edit_button'/>">
                    </div>
                </div>
            </form>

            <#if user.registrationType='COMMON'>
                <@spring.bind "passwordEditForm"/>
                <form method="post" action="profile-password-edit">
                    <#if _csrf??>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    </#if>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="row">
                                <h2 class="text-light"><@spring.message 'profile_edit_page.password_edit_label'/></h2>
                            </div>
                            <#if password_message??>
                                <div class="row">
                                    <div class="col-md-6 text-light">
                                        <label><@spring.message '${password_message}'/></label>
                                    </div>
                                </div>
                            </#if>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile_edit_page.current_password_label'/></label>
                                </div>
                                <div class="col-md-6 text-light">
<#--                                    <input type="password" name="current_password" id="current_password" placeholder="Your Current Password" value="" required minlength="8" maxlength="40">-->
                                    <#assign currentPasswordPlaceholder><@spring.message 'profile_edit_page.current_password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "passwordEditForm.currentPassword" "placeholder='" + currentPasswordPlaceholder + "' required minlength='8' maxlength='40'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile_edit_page.new_password_label'/></label>
                                </div>
                                <div class="col-md-6 text-light">
<#--                                    <input type="password" name="new_password" id="new_password" placeholder="New password" value="" required minlength="8" maxlength="40">-->
                                    <#assign newPasswordPlaceholder><@spring.message 'profile_edit_page.new_password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "passwordEditForm.newPassword" "placeholder='" + newPasswordPlaceholder + "' required minlength='8' maxlength='40'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile_edit_page.repeated_new_password_label'/></label>
                                </div>
                                <div class="col-md-6 text-light">
<#--                                    <input type="password" name="re_new_password" id="re_new_password" placeholder="Repeat new password" value="" required minlength="8" maxlength="40">-->
                                    <#assign repeatedNewPasswordPlaceholder><@spring.message 'profile_edit_page.repeated_new_password_placeholder'/></#assign>
                                    <@spring.formPasswordInput "passwordEditForm.repeatedNewPassword" "placeholder='" + repeatedNewPasswordPlaceholder + "' required minlength='8' maxlength='40'"/>
                                </div>
                                <div class="form-group">
                                    <@spring.showErrors "<br>" "error"/>
                                </div>
                            </div>
                            <div class="row">
                                <input type="submit" class="btn btn-secondary" role="button" value="<@spring.message 'profile_edit_page.password_edit_button'/>">
                            </div>
                        </div>
                    </div>
                </form>
            </#if>

            <@spring.bind 'accountDeleteForm'/>
            <form method="post" action="profile-delete">
                <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </#if>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <h2 class="text-light"><@spring.message 'profile_edit_page.delete_account_label'/></h2>
                        </div>
                        <#if delete_message??>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message '${delete_message}'/></label>
                                </div>
                            </div>
                        </#if>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <label><@spring.message 'profile_edit_page.confirm_string_message'/> DELETE_${user.login}_ACCOUNT</label>
                            </div>
                            <div class="col-md-6 text-light">
                                <#assign confirmStringPlaceholder><@spring.message 'profile_edit_page.confirm_string_placeholder'/></#assign>
                                <@spring.formInput "accountDeleteForm.userConfirm" "required placeholder='" + confirmStringPlaceholder + "'"/>
<#--                                <input type="text" name="delete_password" id="delete_password" placeholder="Your Current Password" value="" required minlength="8" maxlength="40">-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 text-light">
                                <@spring.formCheckbox "accountDeleteForm.isAgree" "class='agree-term' required"/>
                                <label for="userAccess"><span><span></span></span><@spring.message "profile_edit_page.confirm_checkbox_label"/></label>
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" class="btn btn-secondary" role="button" value="<@spring.message 'profile_edit_page.delete_account_button'/>">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <@m.htmlFooter/>
    </body>
</html>