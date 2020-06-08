<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	var showPass = 0;
	$('.btn-show-pass').on('click', function(){
	if(showPass == 0) {
	$(this).next('input').attr('type','text');
	$(this).find('i').removeClass('mdi-eye');
	$(this).find('i').addClass('mdi-eye-off');
	showPass = 1;
	}
	else {
	$(this).next('input').attr('type','password');
	$(this).find('i').addClass('mdi-eye');
	$(this).find('i').removeClass('mdi-eye-off');
	showPass = 0;
	}

	});
	});
</script>
<link rel="stylesheet" href="css/admin.css">
<meta charset="UTF-8">
<title>관리자 페이지 로그인 화면</title>
</head>
<body>
<div class="limit">
    <div class="login-container">
        <div class="bb-login">
            <form method="post" action="admin_login_check" class="bb-form validate-form"> <span class="bb-form-title p-b-26"> Welcome </span> <span class="bb-form-title p-b-48"> <i class="mdi mdi-symfony"></i> </span>
                <div class="wrap-input100 validate-input" data-validate="Valid email is: a@b.c"> <input class="input100" type="text" name="id" placeholder="아이디를 입력해주세요."> </div>
                <div class="wrap-input100 validate-input" data-validate="Enter password"> <span class="btn-show-pass"> <i class="mdi mdi-eye show_password"></i> </span> <input class="input100" type="password" name="pwd" placeholder="비밀번호를 입력해주세요."> </div>
                <div class="login-container-form-btn">
                <b style="color: red;">${message}</b>
                    <div class="bb-login-form-btn">
                        <div class="bb-form-bgbtn"></div> <button class="bb-form-btn"> 로그인 </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>