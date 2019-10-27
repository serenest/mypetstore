<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

	<form action="signOn" method="get">
		<p>Please enter your username and password.</p>
		<p>
			Username: <input type="text" name="username" value="j2ee"/><br />
			Password: <input type="password" name="password" value="j2ee" /><br />
			Verification code:<input type="text" name="inputCode" />
		</p>
		<img id="imgVerifyCode" onclick="changeCode()" src="generateCode" alt="">
		<script type="text/javascript">
			/**
			 * 刷新验证码
			 */
			function changeCode(){
				var time = new Date().getTime();
				document.getElementById("imgVerifyCode").src="<%=request.getContextPath() %>/generateCode?d=" + time;
			}
		</script>
		<input type="submit" name="signOn" value="Login">

		<br>
		${sessionScope.message}
	</form>

	Need a user name and password?
	<a href="newAccountForm">Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

