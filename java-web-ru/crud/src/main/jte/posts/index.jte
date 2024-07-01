@import exercise.dto.posts.PostsPage
@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes

@param PostsPage postsPage

@template.layout.page(
    content = @`
    <div>
        @if(postsPage.getPage() > 1)
            <a href="${NamedRoutes.postsPath()}?page=${postsPage.getPage() - 1}">На предыдущую страницу</a>
        @endif
        <a href="${NamedRoutes.postsPath()}?page=${postsPage.getPage() + 1}">На следующую страницу</a>
        @for(PostPage post : postsPage.getPosts())
            <div>
                <a href="/posts/${post.getPost().getId()}">${post.getPost().getName()}</a>
            </div>
        @endfor
    </div>
`)
