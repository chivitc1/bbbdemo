<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register New User</title>
    <meta name="layout" content="main"/>
</head>

<body>

<h1>Register New User</h1>

<g:hasErrors bean="${user}">
    <ul>
        <g:eachError var="err" bean="${user}">
            <li><g:message error="${err}" /></li>
        </g:eachError>
    </ul>
</g:hasErrors>
<g:if test="${flash.message}">
    <div class="flash">${flash.message}</div>
</g:if>

<g:form method="POST" action="register">
    <fieldset class="form">
        <div class="fieldcontain required">
            <label for="loginId">Login ID</label>

            <g:textField name="loginId" value="${user?.loginId}"/>
        </div>
        <div class="fieldcontain required">
            <label for="password">Password</label>
            <g:passwordField name="password"/>
        </div>
        <div class="fieldcontain required">
            <label for="passwordRepeat">Confirm Password</label>

            <g:passwordField name="passwordRepeat"/>
        </div>
        <div class="fieldcontain required">
            <label for="fullName">Full Name</label>
            <g:textField name="fullName"
                         value="${user?.fullName}"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="register" value="Register"/>
    </fieldset>
</g:form>

</body>
</html>