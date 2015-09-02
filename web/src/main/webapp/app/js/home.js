
var documenter = angular.module('documenter', ['ui.bootstrap','ngRoute','angular-loading-bar']);


// loading bar spinner
documenter.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner"></div>';
}]);

// Overview tab controller
documenter.controller('OverviewController', function($scope, $window, $http) {

	$scope.overview = function() {
		
		console.log('should be calling ..');
		
		$http.get("/app/overview").success(function(response) {
	    	var jobj = angular.fromJson(response);
	    	
	    	$scope.objBreakdown = getObjectBreakdown(jobj.sobjects.sobjects);
	    	$scope.apexClassSize = jobj.apexManifest.length;
	    	
	    	$scope.response = response;
	    
	    });
	}
	
	$scope.overview();
	
});

documenter.controller('ERDiagramController', function($scope) {
	console.log('We have an ER Diagram Controller');
});

documenter.controller('ClassDiagramController', function($scope) {
	console.log('We have an ClassDiagram Controller');
});

documenter.controller('UIPagesController', function($scope) {
	console.log('We have the UIPagesController Controller');
});

documenter.controller('SecurityController', function($scope) {
	console.log('We have the SecurityController');
});

documenter.controller('WorkflowController', function($scope) {
	console.log('We have the Workflow controller');
});

documenter.controller('DataAnlysisController', function($scope) {
	console.log('We have the DataAnalysis controller');
});

documenter.controller('FilesController', function($scope) {
	console.log('We have the Files controller');
});

documenter.controller('InstalledPackagesController', function($scope) {
	console.log('We have the InstalledPackages controller');
});

documenter.controller('DataAnalysisController', function($scope) {
	console.log('WE have the DataAnalysisController controller');
	
})
function getObjectBreakdown(sobjectArray) {
	
	var retval = {};
	retval.custom = 0;
	retval.big = 0;
	retval.external = 0
	
	for (var i = 0; i < sobjectArray.length; i++) {
	    var obj = sobjectArray[i];
	    
	    if(obj.name.indexOf("__c") > -1) {
	    	retval.custom++;
	    } else if(obj.name.indexOf("__b") > -1) {
	    	retval.big++;
	    } else if (obj.name.indexOf("__x") > -1) {
	    	retval.external++;
	    } 
	    // knowledge object types, tags etc here
	}
	return retval;
}
