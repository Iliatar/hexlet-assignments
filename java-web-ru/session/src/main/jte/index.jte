@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
        @if(page.getName() != null){
            <p>${page.getName().toString()}</p>
            <form action="${NamedRoutes.logoutPath()}" method="post">Выйти</form>
        } @else {
            <form action="${NamedRoutes.loginPath()}" method="post">Войти</form>
        }
        @endif
`)
