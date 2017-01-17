var app=angular.module("ScApp",['ngRoute']);

app.config(function($routeProvider){
	$routeProvider.when( '/societes',{
		templateUrl:  'view/societes.html',
		controller:  'ScController'
	}).when( '/societes/:code',{
		templateUrl:  'view/ordres.html',
		controller:  'OrController'
	}).otherwise({
		redirectTo: '/societes'});
});

app.controller("ScController",function($scope,$http,$routeParams,$window){
	$scope.params = $routeParams;
	$scope.motCle="";
	$scope.pageCourante=0;
	$scope.size=5;
	$scope.societes=loadSocietes($scope.pageCourante,$scope.size);
	$scope.ScCode="";
	
	$scope.view=function(){
		if($scope.ScCode!=""){
			$window.location.href="#/societes/"+$scope.ScCode;
			console.log($scope.ScCode);
		}
	}
	
	function loadSocietes(page,size){
		$http.get("societes?&page="+page+"&size="+size)
		.success(function(data){
			$scope.societes=data.content;
			$scope.pages=new Array(data.totalPages);
		});
		return [];
	}
	
	$scope.search=function(){
		$http.get("societes/search?q="+$scope.motCle+"&page="+$scope.pageCourante+"&size="+$scope.size)
			.success(function(data){
				$scope.societes=data.content;
				$scope.pages=new Array(data.totalPages);
			});
	}
	
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.search();
	}
	
	
});

app.controller("OrController",function($scope,$http,$routeParams){
	$scope.params = $routeParams;
	
	$scope.societe=loadSociete($scope.params.code);
	$scope.pageCourante=0;
	$scope.size=3;
	
	$scope.ordres=loadOrdres($scope.params.code,$scope.pageCourante,$scope.size);
	
	$scope.totalVentes=loadTotalVentes($scope.params.code);
	$scope.totalAchats=loadTotalAchats($scope.params.code);
	$scope.avgPrixAchats=loadAvgPrixAchats($scope.params.code);
	$scope.avgPrixVentes=loadAvgPrixVentes($scope.params.code);
	$scope.estimation=loadEstimation($scope.params.code);
	
	function loadSociete(code){
		$http.get("societes/"+code)
			.success(function(data){
				$scope.societe=data;
			});
		return {};
	}
	
	function loadOrdres(code,page,size){
		$http.get("societes/"+code+"/ordres?&page="+page+"&size="+size)
			.success(function(data){
				$scope.ordres=data.content;
				$scope.pages=new Array(data.totalPages);
			});
		return [];
	}
	
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.loadOrdres($scope.params.code,$scope.pageCourante,$scope.size);
	}
	
	function loadTotalVentes(code){
		$http.get("societes/"+code+"/ventes/total")
			.success(function(data){
				if(data!=""){
					$scope.totalVentes=data;
				}
				else $scope.totalVentes="0";
			});
		return "";
	}
	function loadTotalAchats(code){
		$http.get("societes/"+code+"/achats/total")
			.success(function(data){
				if(data!=""){
					$scope.totalAchats=data;
				}
				else $scope.totalAchats="0";
			});
		return "";
	}
	function loadAvgPrixAchats(code){
		$http.get("societes/"+code+"/achats/avgPrix")
			.success(function(data){
				if(data!=""){
					$scope.avgPrixAchats=data;
				}
				else $scope.avgPrixAchats="0";
			});
		return "";
	}
	function loadAvgPrixVentes(code){
		$http.get("societes/"+code+"/ventes/avgPrix")
			.success(function(data){
				if(data!=""){
					$scope.avgPrixVentes=data;
				}
				else $scope.avgPrixVentes="0";
			});
		return "";
	}
	function loadEstimation(code){
		$http.get("societes/"+code+"/estimation")
			.success(function(data){
				if(data!=""){
					$scope.estimation=data;
				}
				else $scope.estimation="0";
			});
		return "";
	}
});