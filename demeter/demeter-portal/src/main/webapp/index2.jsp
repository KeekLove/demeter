
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
    $(function(){
      /*  短信验证码倒计时*/
        var countdownHandler = function(){
            var $button = $(".sendVerifyCode");
            var number = 60;
            var countdown = function(){
                if (number == 0) {
                    $button.attr("disabled",false);
                    $button.html("发送验证码");
                    number = 60;
                    return;
                } else {
                    $button.attr("disabled",true);
                    $button.html(number + "秒 重新发送");
                    number--;
                }
                setTimeout(countdown,1000);
            };
            setTimeout(countdown,1000);
        };
        //发送短信验证码
        $(".sendVerifyCode").on("click", function(){
            var $mobile = $("input[name=mobile]");
            var data = {};
            data.mobile = $.trim($mobile.val());
            if(data.mobile == ''){
                alert('请输入手机号码');
                return;
            }
            var reg = /^1\d{10}$/;
            if(!reg.test(data.mobile)){
                alert('请输入合法的手机号码');
                return ;
            }
            $.ajax({
                url: "/phone/Verification",
                async : true,
                type: "post",
                dataType: "text",
                data: data,
                success: function (data) {
                    if(data == 'success'){
                        countdownHandler();
                        return ;
                    }
                    alert(data);
                }
            });
        });
        //提交
        $(".sub-btn").on("click", function(){
            var data = {};
            data.userId = $.trim($("input[name=userId]").val());
            data.password = $.trim($("input[name=password]").val());
            data.mobile = $.trim($("input[name=mobile]").val());
            data.verifyCode = $.trim($("input[name=verifyCode]").val());
            if(data.userId == ''){
                alert("请输入账号");
                return ;
            }
            if(data.password == ''){
                alert("请输入密码");
                return ;
            }
            if(data.mobile == ''){
                alert("请输入手机号");
                return ;
            }
            if(data.verifyCode == ''){
                alert("请输入验证码");
                return ;
            }
            $.ajax({
                url:"/register",
                async : true,
                type: "post",
                dataType: "text",
                data: data,
                success: function (data) {
                    if(data == 'success'){
                        alert("注册成功");
                        return ;
                    }
                    alert(data);
                }
            });
        })
    });

</script>
<form action="" method="post" enctype="multipart/form-data"><br/>
    <div>
        <label>账号: </label><input name="userId">
    </div>
    <div>
        <label>密码:</label><input name="password">
    </div>
    <div>
        <label>手机号:</label><input name="mobile">
    </div>
    <div>
        <label>验证码:</label>
        <input name="verifyCode">
        <button type="button" class="sendVerifyCode">获取短信验证码</button>
    </div>
    <div><button type="button" class="sub-btn">提交</button></div>

</form>
</body>
</html>
