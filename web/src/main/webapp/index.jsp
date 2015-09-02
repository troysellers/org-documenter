<!DOCTYPE>
<html>
<head>

<link rel="stylesheet" href="public/css/home.css"></link>
<script src="/public/bower/js/jquery/jquery.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div class="header">Salesforce Org Documenter</div>
		<div class="loginBox">
			<div class="loginBoxCmp"><img src="public/img/sfLogo.png" height="100px" /></div>
			<div class="loginBoxCmp"><h2>Login</h2></div>
			
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
				<input id="custom_domain_checkbox" type="checkbox" /> I have a different Salesforce domain.<br />
				<input type="text" id="custom_domain"/>
			</div>
		</div>
	</div>
	<form action="/oauth" method="POST" id="loginForm">
		<input type="hidden" id="env" name="env" />
	</form>
</body>
<script type="text/javascript">
	var j$ = jQuery.noConflict();
	j$(document).ready(function() {
		alert("JQUERY");
		j$("#custom_domain_checkbox").click(function() {
			j$("#custom_domain").toggle();
			j$(".button").toggle();
		});
		
		j$(".btn").click(function() {
			if(j$(this).text() == 'Production') {
				j$("#env").val("login.salesforce.com");
			} else if(j$(this).text() == 'Sandbox') {
				j$("#env").val("test.salesforce.com")
			}  else {
				var custom = j$("#custom_domain").val();
				if(custom.indexOf("://") > -1) {
					custom = custom.substring(custom.indexOf("://")+3, custom.length);	
				}
				j$("#env").val(custom);
			}
			j$("#loginForm").submit();
		});
	});
</script>
</html>