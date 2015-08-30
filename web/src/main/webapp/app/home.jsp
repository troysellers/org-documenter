<html ng-app="documenter">
<head>
	<title>Documenter</title>
	<meta content="text/html; charset=UTF-8" http-equiv="content-type"></meta>
	<meta charset="UTF-8"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"></meta>

	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid Sans"></link>
	<link type="text/css" href="../public/css/home.css" rel="stylesheet"></link>
	<link type="text/css" href="../public/css/bootstrap.css" rel="stylesheet"></link>

</head>
<body>
	<div class="container" ng-controller="HomePageController">
		<div class="header">Salesforce Org Documenter</div>
		<tabset>
			<tab heading="Overview">
				Here is the overview <br />
				Number of Custom Objects - {{objBreakdown.custom}} <br />
				Number of External Objects - {{objBreakdown.external}} <br />
				Number of Big Objects - {{objBreakdown.big}} <br />
				Number of Apex Classes - {{apexClassSize}} <br />
				<hr />
				{{response.sobjects}} 
				<hr />
				{{response.apexManifest}}
			</tab>
			<tab heading="ER Diagram">An ER Diagram</tab>
			<tab heading="Class Diagram">A class diagram</tab>
			<tab heading="VF Pages">Some detail about VF</tab>
			<tab heading="Workflows">Workflows, processes</tab>
			<tab heading="Security">Roles, Profiles, Permissions sets</tab>
			<tab heading="Data Analysis">warn and check for data access</tab>
			<tab heading="Files">Some further information on files</tab>
		</tabset>
	</div>
</body>
<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/ui-bootstrap-tpls.min.js"></script>
<script src="js/home.js" type="text/javascript "></script>
<script src="js/controller.js" type="text/javascript "></script>
</html>