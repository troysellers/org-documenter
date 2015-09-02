<html ng-app="documenter">
<head>
	@import url(https://fonts.googleapis.com/css?family=Droid+Sans);
	<title>Documenter</title>
	<meta content="text/html; charset=UTF-8" http-equiv="content-type"></meta>
	<meta charset="UTF-8"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"></meta>

	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid Sans"></link>
	<link type="text/css" href="../public/css/home.css" rel="stylesheet"></link>
	<link type="text/css" href="../public/bower/components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"></link>
	<link type="text/css" href="../public/bower/css/angular-loading-bar/loading-bar.css" rel="stylesheet"></link>
</head>
<body>
	<div class="container">
		<div class="header">Salesforce Org Documenter</div>
		
		<tabset>
			<tab heading="Overview">
				<div class="partial Overview" ng-controller="OverviewController">
					Here is the overview <br />
					Number of Custom Objects - {{objBreakdown.custom}} <br />
					Number of External Objects - {{objBreakdown.external}} <br />
					Number of Big Objects - {{objBreakdown.big}} <br />
					Number of Apex Classes - {{apexClassSize}} <br />
					<hr />
					{{response.sobjects}} 
					<hr />
					{{response.apexManifest}}
				</div>
			</tab>
			<tab heading="ER Diagram">
				<div ng-include="'partials/erDiagram.html'"></div>
			</tab>
			<tab heading="Class Diagram">
				<div ng-include="'partials/classDiagram.html'"></div>
			</tab>
			<tab heading="UI Analysis">
				<div ng-include="'partials/uiPages.html'"></div>
			</tab>
			<tab heading="Workflows">
				<div ng-include="'partials/workflows.html'"></div>
			</tab>
			<tab heading="Security">
				<div ng-include="'partials/security.html'"></div>
			</tab>
			<tab heading="Data Analysis">
				<div ng-include="'partials/dataAnalysis.html'"></div>
			</tab>
			<tab heading="Files">
				<div ng-include="'partials/files.html'"></div>
			</tab>
		</tabset>
	</div>
</body>
<script src="../public/bower/js/angularjs/angular.js"></script>
<script src="../public/bower/js/angular-route/angular-route.js"></script>
<script src="../public/bower/js/angular-loading-bar/loading-bar.js"></script>
<script src="../public/bower/js/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="js/home.js" type="text/javascript "></script>
<script src="js/controller.js" type="text/javascript "></script>
</html>