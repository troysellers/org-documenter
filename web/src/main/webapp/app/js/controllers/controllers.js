
'use strict';

var documentControllers = angular.module('documenterControllers',[]);

documentControllers.controller('DocumenterController', ['$scope',function($scope) {
	
	var tabActive = 'slds-active';
	var tabNotActive = '';
	var divVisible = '';
	var divHidden = 'slds-hide';
	
	$scope.tabs = [
	               {title:'Overview',include:'partials/overview.html',tab:tabActive, div:divVisible, tabHtmlIndex:0, ariaSelected:true},
	               {title:'ER Diagram',include:'partials/erDiagram.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'Class Diagram',include:'partials/classDiagram.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'UI Pages',include:'partials/uiPages.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'Workflows',include:'partials/workflows.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'Security',include:'partials/security.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'Data Analysis',include:'partials/dataAnalysis.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false},
	               {title:'Files',include:'partials/files.html',tab:tabNotActive, div:divHidden, tabHtmlIndex:-1, ariaSelected:false}
	
	];
	
	$scope.tabClick = function(tab) {
		angular.forEach($scope.tabs, function(t) {
			if(t === tab) {
				t.tab = tabActive;
				t.div = divVisible;
				t.ariaSelected = true;
				t.tabHtmlIndex = 0;
			} else {
				t.tab = tabNotActive;
				t.div = divHidden;
				t.ariaSelected = false;
				t.tabHtmlIndex = -1;
			}		
		});
	}
}]);

//Overview tab controller
documentControllers.controller('OverviewController', ['$scope','ObjectDataFactory', function($scope, ObjectDataFactory) {

	var objectData = ObjectDataFactory.success(function (result) {
			$scope.data = result;
			$scope.objBreakdown = getObjectBreakdown(result.sobjects.sobjects);
			console.log(JSON.stringify(result.sobjects.sobjects));
	});

	$scope.showSObjects = {
			label: "Show SObject Data",
			visible: false
	};
	
	$scope.showApexManifest = {
			label: "Show Apex Manifest",
			visible: false
	};
	
	$scope.toggleSObjects = function () {
		$scope.showSObjects.visible = !$scope.showSObjects.visible;
		$scope.showSObjects.label = ($scope.showSObjects.visible ? "Hide SObject Data" : "Show SObject Data");
	}
	$scope.toggleApexManifest = function () {
		$scope.showApexManifest.visible = !$scope.showApexManifest.visible;
		$scope.showApexManifest.label = ($scope.showApexManifest.visible ? "Hide Apex Manifest" : "Show Apex Manifest");
	}
}]);


documentControllers.controller('ERDiagramController', ['$scope',function($scope) {
	// console.log('We have an ER Diagram Controller');
}]);

documentControllers.controller('ClassDiagramController', ['$scope',function($scope) {
	// console.log('We have an ClassDiagram Controller');
}]);

documentControllers.controller('UIPagesController', ['$scope',function($scope) {
	// console.log('We have the UIPagesController Controller');
}]);

documentControllers.controller('SecurityController', ['$scope',function($scope) {
	// console.log('We have the SecurityController');
}]);

documentControllers.controller('WorkflowController', ['$scope',function($scope) {
	// console.log('~~ in the workflow controller');
	
}]);

documentControllers.controller('DataAnlysisController', ['$scope',function($scope) {
	console.log('We have the DataAnalysis controller');
}]);

documentControllers.controller('FilesController', ['$scope',function($scope) {
	// console.log('We have the Files controller');
}]);

documentControllers.controller('InstalledPackagesController', ['$scope',function($scope) {
	// console.log('We have the InstalledPackages controller');
}]);

documentControllers.controller('DataAnalysisController', ['$scope',function($scope) {
	// console.log('WE have the DataAnalysisController controller');
}]);

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


