@import exercise.dto.users.UsersPage
@param UsersPage users

<html>
    <head>
        <title>Пользователи</title>
        <meta charset="utf-8" />
    </head>
    <body>
        <h2>Список пользователей</h2>
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
                @for(var user : users.getUsers())
                    <tr>
                        <td>${user.getId()}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
            </tbody>
        </table>
    </body>
</html>

