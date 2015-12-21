'use strict';

var documenterFactories = angular.module('documenterFactories',[]);

documenterFactories.factory('ObjectDataFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/overview'});

}]);

documenterFactories.factory('SObjectDataFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/sobjects'});
}]);

documenterFactories.factory('ApexClassFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/apexClasses'});
}]);

documenterFactories.factory('WorkflowFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/workflows'});
}]);