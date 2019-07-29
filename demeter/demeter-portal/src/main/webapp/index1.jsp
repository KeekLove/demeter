
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
<h2>这是失败后的产物</h2>
<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="public/js/jquery-migrate-1.0.0.js"></script>
<script type="text/javascript">
    $(function () {
        $("#sell").click(function () {
            var phone=$("phone").val();
            $.get("/phone/Verification",
                {phone:phone},
                function (data) {

                }
            )
        })
    });


</script>
<form action="" method="post" enctype="multipart/form-data"><br/>
    <div>
        <input type="text" name="phone" id="phone">
    </div>
    <div>
        <input type="text"/>
        <input type="submit" value="发送短信" id="sell" />
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
