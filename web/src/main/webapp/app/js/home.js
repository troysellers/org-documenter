'use strict';

var documenter = angular.module('documenter', ['ui.bootstrap','ngRoute','angular-loading-bar','documenterFactories','documenterControllers']);

// loading bar spinner
documenter.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner"></div>';
}]);



