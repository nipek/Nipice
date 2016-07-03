var app = angular.module('Nipice', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
         // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  })
})
.config(function($stateProvider, $urlRouterProvider,$ionicConfigProvider) {
 if (ionic.Platform.isAndroid()) {
      $ionicConfigProvider.scrolling.jsScrolling(false);
    };
  $stateProvider 
  .state('intro', {
    url: '/',
     templateUrl: 'templates/intro.html',
    controller: 'IntroCtrl'
  })
  .state('nipice', {
    url: '/nipice',
    templateUrl: 'templates/nipice.html',
    controller: 'nipiceCtrl'
  })
  .state('Lastpage', {
    url: '/result',
    templateUrl: 'templates/result.html',
    controller: 'nipiceCtrl'
  });

  $urlRouterProvider.otherwise("/");

})



