$(function () {
    $("#registbtn").click(function() {
        var user = {
            mail:$("#mail").val()
        }
        $.ajax({
            type: "POST",
            url:"/mail.action",
            contentType: "application/json;",
            data: JSON.stringify(user),
            success: function (data) {
                if(data == "successful"){
                    alert("已发送至您的邮箱")
                    location.href="/login.html";
                }
                if (data=="failful") {
                    alert("邮箱错误")
                }
            },
            error: function (data) {
                alert("网络繁忙")
            }
        });

    });
});