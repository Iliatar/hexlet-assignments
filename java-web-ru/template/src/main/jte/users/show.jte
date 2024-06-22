@import exercise.dto.users.UserPage
@param UserPage user

<html>
    <head>
        <title>${user.getUser().getFirstName() + " " + user.getUser().getLastName()}</title>
        <meta charset="utf-8" />
    </head>
    <body>
        <h1>${user.getUser().getFirstName() + " " + user.getUser().getLastName()}</h1>
        <p>id: ${user.getUser().getId()}</p>
        <p>email: ${user.getUser().getEmail()}</p>
    </body>
</html>
