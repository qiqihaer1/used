
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
    	 //获取界面上 用户名 密码 （判断合法性）
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        
		//异步的方式传输给后台，后台将结果返回
		$.getJSON("login.do",{loginname:username,password:password},function(data){
			if(data.flag){
				//成功:跳转到 index.html
				location.href = "index.jsp";
			}else{
				//失败:显示消息
				$(".connect p").text(data.msg);
			}
		});
        
        return false;
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});
