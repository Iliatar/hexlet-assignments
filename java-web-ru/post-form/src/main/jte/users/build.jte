@template.layout.page(
    content = @`
<form action="/users" method="post">
<div>
<label>Имя
<input type="text" name="firstName">
</label>
</div>
<div>
<label>Фамилия
<input type="text" name="lastName">
</label>
</div>
<div>
<label>емейл
<input type="text" name="email">
</label>
</div>
<div>
<label>Пароль
<input type="text" name="password">
</label>
</div>
<div>
<input type="submit" name="Создать">
</div>
</form>
`
)
