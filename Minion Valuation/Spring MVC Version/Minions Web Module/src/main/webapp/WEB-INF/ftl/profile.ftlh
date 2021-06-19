<#import "macro.ftlh" as m/>
<#import "spring.ftl" as spring/>


<html lang="en">
    <head>
        <@m.htmlHeader title="${user.login}"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/profile/profile.css' />">
        <script type ="text/javascript" src="<@spring.url '/static/js/profile/profile-main.js' />"></script>
    </head>
    <body>
        <#if user.login??>
            <@m.pageHeader login="${user.login}"/>
        <#else>
            <@m.pageHeader/>
        </#if>
        <div class="container emp-profile bg-dark">
            <div class="row">
                <div class="col-md-4">
                    <div class="profile-img">
                        <img src="<@spring.url '/static/images/users-images/${user.image}.png' />" alt=""/>
                    </div>
                </div>
                <div class="col-md-6">

                    <#if message??>
                        <p class="text-success"><@spring.message '${message}'/></p>
                    </#if>

                    <div class="profile-head">
                        <h5 class="text-light">
                            ${user.login}
                        </h5>
                        <#if user.role="USER">
                            <h6 class="text-success">
                                <@spring.message 'profile.user_role'/>
                            </h6>
                        <#elseif user.role="ADMIN">
                            <h6 class="text-danger">
                                <@spring.message 'profile.admin_role'/>
                            </h6>
                        <#elseif user.role="MODERATOR">
                            <h6 class="text-warning">
                                <@spring.message 'profile.moder_role'/>
                            </h6>
                        <#elseif user.role="TESTER">
                            <h6 class="text-primary">
                                <@spring.message 'profile.tester_role'/>
                            </h6>
                        <#else>
                            <h6>
                                ${user.role}
                            </h6>
                        </#if>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><@spring.message 'profile.main_info_option'/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><@spring.message 'profile.about_option'/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2">
                    <a class="btn btn-secondary" href="${mvcUrl.fromMappingName('PC#getProfileEditPage').build()}" role="button"><@spring.message 'profile.edit_profile_button'/></a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.first_name_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.firstName ??>
                                            ${user.firstName}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.minecraft_nickname_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.minecraftNickname ??>
                                            ${user.minecraftNickname}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.email_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.email ??>
                                            ${user.email}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.gender_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.gender ??>
                                            ${user.gender.value}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.country_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.country ??>
                                            ${user.country}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.vk_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.vk ??>
                                            ${user.vk}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-light">
                                    <label><@spring.message 'profile.facebook_title'/></label>
                                </div>
                                <div class="col-md-6 text-light">
                                    <p>
                                        <#if user.facebook ??>
                                            ${user.facebook}
                                        <#else>
                                            <@spring.message 'profile.undefined_field'/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <@m.htmlFooter/>
    </body>
</html>