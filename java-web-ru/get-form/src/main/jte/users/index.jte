@import exercise.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(
content = @`
    <p>Пользователи</p>
    <form action="/users" method="get">
        <input type="search" name="term" value="${usersPage.getTerm()}"/>
        <input type="submit" name="Поиск"/>
    </form>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>email</th>
        </tr>
        </thead>
        <tbody>
        @for(var user : usersPage.getUsers())
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
        </tbody>
    </table>
`
)