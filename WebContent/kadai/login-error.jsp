<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<link rel="stylesheet" href="templates/style.css">

<p class="title">得点管理システム</p>

<p style="color:red; text-align:center;">IDまたはパスワードが確認できませんでした</p>

<form action="LoginExecute.action" method="post">
<p class="login-form">ログイン</p>
<p><input type="text" name="id" placeholder="ID" class="input-box" required value="${param.id != null ? param.id : ''}">
</p>
<p><input type="password" name="password" placeholder="パスワード"id="password" class="input-box" required></p>
<p><input type="checkbox" name="chk_d_ps" onclick="togglePassword()"><label for="showPassword">パスワードを表示</label>
<p><input type="submit" value="ログイン" class="login-button"></p>
</form>

<script>
function togglePassword() {
  const passwordField = document.getElementById("password");
  if (passwordField.type === "password") {
    passwordField.type = "text";
  } else {
    passwordField.type = "password";
  }
}
</script>

<%@include file="../footer.html" %>