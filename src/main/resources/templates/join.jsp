<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
계정 정보를 입력해주세요!

<form action="/joinUs" method="post">
    <div>
        <label for="id">아이디: </label>
        <input id="id" name="id" type="text">
    </div>
    <div>
        <label for="password">패스워드: </label>
        <input id="password" name="password" type="password">
    </div>

    <input type="submit" value="가입하기">
</form>
</body>
</html>
