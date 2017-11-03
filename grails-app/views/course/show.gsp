<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-course" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form >
                <fieldset class="form">
                    <div class="fieldcontain required">
                        <label for="name">Name: </label>
                        <span id="name">${course?.name}</span>
                    </div>

                    <div class="fieldcontain">
                        <label for="startingTime">Starting Date: </label>
                        <span id="startingTime" >${course?.startingTime}</span>
                    </div>

                    <div class="fieldcontain">
                        <label for="teacher">Teacher: </label>
                        <span id="teacher" >${course?.teacher}</span>
                    </div>

                    <div class="fieldcontain">
                        <label for="room">Room: </label>
                        <span id="room" >${course?.room}</span>
                    </div>
                </fieldset>
            </g:form>

            <g:form resource="${this.course}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.course}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
