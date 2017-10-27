<table>
    <thead>
    <th>User Login ID</th>
    <th>Full Name</th>
    <th>Password</th>
    </thead>
    <tbody>
    <g:each in="${users}" var="user" >
        <tr>
            <td>${user.loginId}</td>
            <td>${user.fullName}</td>
            <td>${user.password}</td>
        </tr>
    </g:each>
    </tbody>
</table>


<div class="pagination">
    <g:paginate total="${count ?: 0}"/>
</div>