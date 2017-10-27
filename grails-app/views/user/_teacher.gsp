<g:if test="${!teacherCourseList || teacherCourseList.size() < 1}">
    <p><strong>You are not a teacher</strong></p>
</g:if>
<g:if test="${teacherCourseList && teacherCourseList.size() >= 1}">
    <p><strong>You are a teacher of the following rooms:</strong></p>
    <ul>
        <g:each in="${teacherCourseList}" var="course">
            <li>${course.name}
            <g:if test="${course?.room}">
                <g:link controller="bbbCall" action="joinRoom" params="${[roomId:course.room.id, role: "ROLE_MODERATOR"]}">Join</g:link>
            </g:if>
            </li>
        </g:each>
    </ul>
</g:if>