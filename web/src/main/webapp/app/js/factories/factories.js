'use strict';

var documenterFactories = angular.module('documenterFactories',[]);

documenterFactories.factory('ObjectDataFactory', ['$http', function($http) {
	
	return $http({method: 'GET', url:'/app/overview'});

}]);