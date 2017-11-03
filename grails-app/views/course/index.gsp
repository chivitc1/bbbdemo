<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-course" class="content scaffold-list" role="main">
            <h1>Course List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:if test="${courseList.size() > 0}">
                <table>
                    <thead>
                    <th>Name</th>
                    <th>Starting Date</th>
                    <th>Teacher</th>
                    <th>Room</th>
                    </thead>
                    <tbody>
                    <g:each in="${courseList}" var="course">
                        <tr>
                            <td><a href="show/${course.id}">${course.name}</a></td>

                            <td>${course.startingTime}</td>
                            <td>${course.teacher}</td>
                            <td>${course.room}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p class="alert">Currently has no room</p>
            </g:else>
        </div>
    </body>
</html>