
<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2019/7/23
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="public/js/jquery-migrate-1.0.0.js"></script>
<script type="text/javascript">
   /* function go() {
        var Number=window.document.getElementById("phoneNumber").value;
        window.open("/phone/code?phoneNumber="+Number);
        window.alert("!发送验证码")
    }*/
    $(function () {
        $("#go").click(function () {
            var Number=$("#phoneNumber").val();
            var password=$("#password").val();
            $.get("/phone/code",
                {'phoneNumber':Number,'password':password},
                function (data) {

                }
            )
        })
    })
</script>
<form action="/phone/Verification" method="get"><br/>
    账号:<input type="text" name="phoneNumber" id="phoneNumber" placeholder="手机号码"
    pattern="^1(3|4|5|7|8)\d{9}$" required="required"><br/>
    密码:<input type="password" name="password" id="password" placeholder="password" required="required"><br/>
    验证码:<input type="text" name="code" placeholder="验证码" required="required"><br/>
    <button type="button" onclick="go()" id="go">发送验证码</button>
    <button type="submit">注册</button>
</form>
</body>
</html>
