@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
<html>
<head>
    <title>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</title>
    <meta charset="utf-8" />
</head>
<body>
<h1>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</h1>
<p>id: ${page.getUser().getId()}</p>
<p>email: ${page.getUser().getEmail()}</p>
</body>
</html>
`
)
