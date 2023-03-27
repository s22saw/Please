$(function(){

	// 로그인 화면에서 로그인 버튼 클릭 시 Action (/member/login)
	$("#loginBtn").click(function(){		
		let userID = $("#userID").val().trim();
		$("#userID").val(userID);
		let userPW = $("#userPW").val().trim();		
		$("#userPW").val(userPW);

		if (userID == "") {
			alert("아이디를 입력해주세요.");
			$("#userID").focus();
			return;
		} else if (userPW == "") {
			alert("비밀번호를 입력해주세요.");
			$("#userPW").focus();
			return;
		} else {			
		}
	});

});


