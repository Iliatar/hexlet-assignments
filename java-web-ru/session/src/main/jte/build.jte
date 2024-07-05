@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page 

@template.layout.page(
    content = @`
        @if (page.getError() != null) {
            <p>${page.getError()}</p>
        }
        @endif
        <form action="${NamedRoutes.loginPath()}" method="post">
        <input type="text" placeholder="Nickname" name="name" value="${page.getName()}"/>
        <input type="password" placeholder="Password" name="password" />
        <input type="submit" />
</form>
`)
