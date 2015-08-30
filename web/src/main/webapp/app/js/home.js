
var documenter = angular.module('documenter', ['ui.bootstrap','ngRoute']);


documenter.controller('HomePageController', function($scope, $window, $http) {

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
