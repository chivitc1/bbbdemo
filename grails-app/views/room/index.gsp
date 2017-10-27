<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'room.label', default: 'Room')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-room" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-room" class="content scaffold-list" role="main">
    <h1>Room list</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:if test="${roomList.size() > 0}">
        <table>
            <thead>
            <th>Meeting ID</th>
            <th>Name</th>
            <th>Start time</th>
            <th>Action</th>
            </thead>
            <tbody>
            <g:each in="${roomList}" var="room">
                <tr>
                    <td>${room.id}</td>
                    <td>${room.name}</td>
                    <td>${room.startTime}</td>
                    <td>
                        <g:link controller='bbbCall' action='closeRoom' params="${[roomId:room.id]}">Close</g:link> |
                        <g:link controller='bbbCall' action='joinRoom' params="${[roomId:room.id]}">Join</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>
    <g:else>
        <p class="alert">Currently has no room</p>
    </g:else>


    <div class="pagination">
        <g:paginate total="${roomCount ?: 0}"/>
    </div>
</div>
</body>
</html>