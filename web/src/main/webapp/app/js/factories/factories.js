'use strict';

var documenterFactories = angular.module('documenterFactories',[]);

documenterFactories.factory('ObjectDataFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/overview', cache: true});

}]);

documenterFactories.factory('SObjectDataFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/sobjects', cache: true});
}]);

documenterFactories.factory('ApexClassFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/apexClasses', cache: true});
}]);

documenterFactories.factory('WorkflowFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/workflows', cache: true});
}]);