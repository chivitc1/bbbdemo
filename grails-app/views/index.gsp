<%@ page import="inet.Room" %>
<!DOCTYPE html>
<html>
<head>

    <meta name="layout" content="main"/>
    <title>Grails App demo</title>
    <g:javascript library="jquery"/>

</head>
<body>

<h1>Demo BBB</h1>
<div id="adminsrc" style="border: 2px solid; margin: 5px">
    <h2><strong>Admin screen</strong></h2>
    <hr/>
    <div id="menu1">
        <ul>
            <li><g:link controller="room" action="index">Manage Room</g:link> </li>
            <li><g:link controller="course" action="index">Manage Course</g:link> </li>
        </ul>
    </div>

</div>

<div class="usersrc" style="border: 2px solid; margin: 5px">
    <h2><strong>User screen</strong></h2>
    <hr/>

    <div id="teachcourse" style="border-bottom: 2px solid gray; margin-top: 5px">
        <bbb:teacherCourses/>
    </div>

    <div id="registercourse" style="border-bottom: 2px solid gray; margin-top: 5px">
        <g:if test="${flash.registerMessage}">
            <div class="message" role="status">${flash.registerMessage}</div>
        </g:if>
        <bbb:registeringCourses/>
    </div>


</div>


</body>
</html>