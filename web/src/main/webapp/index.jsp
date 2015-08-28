<html>
<head>
	<title>Documenter</title>
	<meta content="text/html; charset=UTF-8" http-equiv="content-type"></meta>
	<meta charset="UTF-8"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"></meta>
	<link type="text/css" href="app.css" rel="stylesheet"></link>
</head>
<body>
<div class="container">
	<div class="header">
		Salesforce Org Documenter
	</div>
	
	<div class="breadcrumb">
		<form method="POST" action="/oauth">
			<select id="env" name="env">
				<option value="login.salesforce.com">Production</option>
				<option value="test.salesforce.com">Sandbox</option>	
			</select>
			<input type="submit" value="submit" />
		</form>
		<a href="/logout">Logout</a>
	</div>
</div>

</body>
<script src="index.js" type="text/javascript "></script>
</html>