var app=angular.module('ScApp',['ui.router']);
var clientSocket=io.connect("http://localhost:8686")

app.config(function($stateProvider,$urlRouterProvider){
	$stateProvider.state('societes',{
		url:'/societes',
		templateUrl:'views/societes.html',
		controller: 'ScCtrl'
	});
	$stateProvider.state('ordres',{
		url:'/societes/:code',
		templateUrl:'views/ordres.html',
		controller: 'OrCtrl'
	});
	$urlRouterProvider.otherwise('societes');
});

app.controller('ScCtrl',function($scope,$rootScope,$window,$http){
	
	$scope.pageCourante=0;
	$scope.size=5;
	$scope.ScCode="";
	$scope.societes=loadSocietes($scope.pageCourante);
	
	function loadSocietes(page){
		clientSocket.emit('chargerSocietes',{page:page,size:$scope.size});
		clientSocket.on('societesLoaded',function(data){
			$scope.societes=data.societes;
			$scope.pages=new Array(data.totalPages);
			$rootScope.$apply();
		});
		return [];
	}
	
	$scope.view=function(){
		if($scope.ScCode!=""){
			$window.location.href="#!/societes/"+$scope.ScCode;
			//console.log($scope.ScCode);
		}
	}
	
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		loadSocietes($scope.pageCourante);
	}
	
	clientSocket.on('newSociete',function(data){
		loadSocietes($scope.pageCourante);
		console.log("newSociete");
	});
	
});

app.controller('OrCtrl',function($scope,$rootScope,$stateParams ){
	$scope.params = $stateParams ;
	$scope.pageCourante=0;
	$scope.size=3;
	
	$scope.societe=loadSociete();
	$scope.ordres=loadOrdres($stateParams.code,$scope.pageCourante);
	
	$scope.totalVentes=loadTotalVentes();
	$scope.totalAchats=loadTotalAchats();
	
	function loadSociete(){
		console.log($stateParams.code);
		clientSocket.emit('chargerSociete',{codeSc:$stateParams.code});
		clientSocket.on('societeLoaded',function(data){
			$scope.societe=data.societe;
			$rootScope.$apply();
		});
		return {};
	}
	
	function loadOrdres(code,page){
		clientSocket.emit('chargerOrdres',{codeSc:$stateParams.code,page:page,size:$scope.size});
		clientSocket.on('ordresLoaded',function(data){
			$scope.ordres=data.ordres;
			$scope.pages=new Array(data.totalPages);
			$rootScope.$apply();
		});
		return [];
	}
	
	function loadTotalVentes(){
		clientSocket.emit('chargerTotalVentes',{codeSc:$stateParams.code});
		clientSocket.on('totalVentesLoaded',function(data){
			$scope.totalVentes=data.totalVentes;
			$rootScope.$apply();
		});
		return "";
	}
	
	function loadTotalAchats(){
		clientSocket.emit('chargerTotalAchats',{codeSc:$stateParams.code});
		clientSocket.on('totalAchatsLoaded',function(data){
			$scope.totalAchats=data.totalAchats;
			$rootScope.$apply();
		});
		return "";
	}
	
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		loadOrdres($scope.params.code,$scope.pageCourante);
	}
	
	clientSocket.on('newOrdre',function(data){
		loadOrdres($scope.params.code,$scope.pageCourante);
		loadTotalVentes();
		loadTotalAchats();
		console.log("newOrdre");
	});
	
});