@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
    <a href="/posts">К списку постов</a>
        <div>
            <h2>Пост ${page.getPost().getId()}</h2>
            <p>Название</p>
            <p>${page.getPost().getName()}</p>
            <p>Содеражимое</p>
            <p>${page.getPost().getBody()}</p>
        </div>
`)