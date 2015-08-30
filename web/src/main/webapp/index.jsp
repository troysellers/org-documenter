<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="app.css"></link>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid Sans"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="header">Salesforce Org Documenter</div>
		<div class="loginBox">
			<div class="loginBoxCmp"><img src="web/img/sfLogo.png" height="100px" /></div>
			<div class="loginBoxCmp"><h2>Login</h2></div>
			<form action="/oauth" method="POST" id="loginForm">
				<input id="env" name="env" />
				<div class="loginBoxCmp button">
					<a class="btn">Production</a>
				</div>
				<div class="loginBoxCmp button">
					<a class="btn">Sandbox</a>
				</div>
				<div class="loginBoxCmp button" style="display:none;">
					<a class="btn">Custom</a>
				</div>
				<div class="loginBoxCmp">
					<input id="custom_domain_checkbox" type="checkbox" checked="checked" /> I have a different Salesforce domain.<br />
					<input type="text" id="custom_domain"/>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#custom_domain_checkbox").click(function() {
			$("#custom_domain").toggle();
			$(".button").toggle();
		});
		
		$(".btn").click(function() {
			if($(this).text() == 'Production') {
				$("#env").val("login.salesforce.com");
			} else if($(this).text() == 'Sandbox') {
				$("#env").val("test.salesforce.com")
			}  else {
				var custom = $("#custom_domain").val();
				if(custom.indexOf("://") > -1) {
					custom = custom.substring(custom.indexOf("://")+3, custom.length);	
				}
				$("#env").val(custom);
			}
			$("#loginForm").submit();
		});
	});
</script>
</html>