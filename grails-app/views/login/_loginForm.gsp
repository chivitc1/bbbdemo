<g:form resource="${this.user}" method="POST" controller="login" action="login">
        <div style="display: inline-block">
            User: <input type="text" name="loginId" />
            Password: <input type="password" name="password" />
            <input type="submit" value="Login" />
        </div>
    <input type="hidden" name="cName" value="${cName}" />
    <input type="hidden" name="aName" value="${aName }" />

</g:form>