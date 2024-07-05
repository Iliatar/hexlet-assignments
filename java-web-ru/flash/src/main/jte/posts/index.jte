@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    content = @`
        @for (Post post : page.getPostList()) {
            <div>
                <p><a href="${NamedRoutes.postPath(post.getId())}"></a>${post.getId()} ${post.getName()}</p>
            </div>
        }
        @endfor
    `,
)
