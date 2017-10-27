<g:if test="${!registeringCourseList || registeringCourseList.size() < 1}">
    <p><strong>No course available for register</strong></p>
</g:if>
<g:if test="${registeringCourseList && registeringCourseList.size() >= 1}">
    <p><strong>You can register the following courses:</strong></p>
    <ul>
        <g:each in="${registeringCourseList}" var="course">
            <li>${course.name}
            <g:if test="${1 == 1}">
                <g:link controller="course" action="register" params="${[courseId: course.id]}">Register as attendee</g:link>
            </g:if>
            <g:if test="${course.room && inet.Course.hasAttendee(session?.user.loginId, course.id)}">
                <g:link controller="course" action="joinRoom" params="${[courseId: course.id]}">Join</g:link>
            </g:if>
            </li>
        </g:each>
    </ul>
</g:if>