// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
var url="http://localhost:8080";
app=angular.module('starter', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
});

app.config(function($stateProvider,$urlRouterProvider){
  $stateProvider.state("societes",{
    url:"/societes",
    templateUrl:"templates/societes.html",
    controller:"ScCtrl"
  });
  $stateProvider.state("chercher",{
    url:"/chercher",
    templateUrl:"templates/chercher.html",
    controller:"ScCtrl"
  });
  $stateProvider.state("contact",{
    url:"/contact",
    templateUrl:"templates/about.html"
  });
  $stateProvider.state("show",{
    url:"/show/:code",
    templateUrl:"templates/showSociete.html",
    controller:"OrCtrl"
  });
  $urlRouterProvider.otherwise("societes");
});

app.controller("ScCtrl",function($scope,$state,$http){
  $scope.size=5;
  $scope.page=-1;
  $scope.countPage=0;
  $scope.societes=[];
  $scope.url=url;
  $scope.moreDataCanBeLoaded=true;

  function loadSocietes(page){
    $http.get(url+"/societes?page="+page+"&size="+$scope.size)
        .success(function(data){
          $scope.countPage=data.totalPages-1;
          data.content.forEach(function(sc){
            $scope.societes.push(sc);
          });
          $scope.$broadcast("scroll.infiniteScrollComplete");
        })
        .error(function(err){
          alert('err scts');
        });
  }


  $scope.loadMore=function(){
    ++$scope.page;
    if($scope.page<=$scope.countPage){
      loadSocietes($scope.page);
    }
    else{
      $scope.moreDataCanBeLoaded=false;
    }
  }

  $scope.getSociete=function(code){
    $state.go("show",{
      code:code
    });
  }
});

app.controller("OrCtrl",function($scope,$stateParams,$http,$ionicLoading){
  $scope.size=4;
  $scope.page=-1;
  $scope.countPage=0;
  $scope.url=url;
  $scope.ordres=[];
  $ionicLoading.show({
    template:"Chargement ... "
  });
  $scope.moreDataCanBeLoaded=true;

  $http.get(url+"/societes/"+$stateParams.code)
      .success(function(data){
        $scope.societe=data;
        $ionicLoading.hide();
      })
      .error(function(err){
        alert('err');
        $ionicLoading.hide();
      });

  function  loadOrdres(page){
    $http.get(url+"/societes/"+$stateParams.code+"/ordres?page="+page+"&size="+$scope.size)
        .success(function(data){
          $scope.countPage=data.totalPages-1;
          $ionicLoading.hide();
          data.content.forEach(function(or){
            $scope.ordres.push(or);
          });
          $scope.$broadcast("scroll.infiniteScrollComplete");
        }).error(function(err){
          alert('err ords');
          $ionicLoading.hide();
        });
  }

  $scope.loadMore=function(){
    ++$scope.page;
    if($scope.page<=$scope.countPage){
      loadOrdres($scope.page);
    }
    else{
      $scope.moreDataCanBeLoaded=false;
    }
  }
});

