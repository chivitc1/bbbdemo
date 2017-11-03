<g:form resource="${this.user}" method="POST" controller="login" action="register">
    <div style="display: inline-block">
        User: <input type="text" name="loginId" placeholder="enter a username"/>
        Password: <input type="password" name="password" placeholder="enter password"/>
        Confirm Password: <input type="password" name="password" placeholder="repeat password"/>
        <input type="submit" value="Submit" />
    </div>
    <input type="hidden" name="cName" value="${cName}" />
    <input type="hidden" name="aName" value="${aName }" />

</g:form>