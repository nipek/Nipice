app.controller('IntroCtrl', function($scope,$rootScope,$state, $ionicSlideBoxDelegate,$ionicPopup,$ionicLoading,$timeout) {
    //Called to navigate to the main app
  $scope.startApp = function() {
 
      $ionicLoading.show({
    content: 'Loading',
    animation: 'fade-in',
    showBackdrop: true,
    maxWidth: 200,
    showDelay: 0,
  });
         $timeout(function () {
       $state.go('nipice');
             $ionicLoading.hide();
      }, 2000); 
   
  };
  $scope.next = function() {
    $ionicSlideBoxDelegate.next();
  };
  $scope.previous = function() {
    $ionicSlideBoxDelegate.previous();
  };

  // Called each time the slide changes
  $scope.slideChanged = function(index) {
    $scope.slideIndex = index;
  };
    $scope.showPopup = function() {
  $rootScope.data = {}
  // An elaborate, custom popup
  var myPopup = $ionicPopup.show({
    template: '<input type="text" ng-model="data.userName">',
    title: 'Enter your name',
    subTitle: 'Save name when it is entered',
    scope: $scope,
    buttons: [
      { text: 'Cancel',
       type: 'button-assertive'},
      {
        text: '<b>Save</b>',
        type: 'button-balanced',
        onTap: function(e) {
          if (!$rootScope.data.userName) {
            //don't allow the user to close unless he enters name
            e.preventDefault();
          } else {
                                     return $rootScope.data.userName;
                       }
        }
      }
    ]
       });
  myPopup.then(function(res) {
    console.log('Tapped!', res);
  });
//  $timeout(function() {
//     myPopup.close(); //close the popup after 3 seconds for some reason
//  }, 10000);
};
       $scope.disclaimer = function() {
     var alertPopup = $ionicPopup.alert({
       title: 'DISCLAIMER',
       template: 'This Appplication is not an oracle, but it would help you calculate your worth in 10 years.How you get there is up to you.We can neither confirm nor deny that this App is real, or a joke.'
     });
     alertPopup.then(function(res) {
       console.log('Thank for reading the dislaimer');
     });
   };
})

.controller('nipiceCtrl', function($scope,$rootScope, $state,$ionicSlideBoxDelegate,$ionicLoading,$timeout,$ionicPopup) {
  console.log('nipiceCtrl');
     $scope.CurrentDate = new Date();
   $scope.slideChanged = function(index) {
    $scope.slideIndex = index;
  };
    $scope.toIntro = function(){
             $state.go('intro');
        var begin = 0;
             $rootScope.result = begin;
        } ;
    
  $scope.back = function() {
    $ionicSlideBoxDelegate.previous();
  };
    var value_ = 0;
      $scope.pageOne = function(value){
         value_ = value * 1;
                          $scope.aa = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
   $scope.pageTwo = function(value){
         value_ = value * 1;
                          $scope.bb = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
   $scope.pageThree = function(value){
         value_ = value * 1;
                          $scope.cc = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
   $scope.pageFour = function(value){
         value_ = value * 1;
                          $scope.dd = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
     $scope.pageFive = function(value){
         value_ = value * 1;
                          $scope.ee = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
     $scope.pageSix = function(value){
         value_ = value * 1;
                          $scope.ff = value_;
                  $ionicSlideBoxDelegate.next();
   } ;  
$scope.pageSeven = function(value){
         value_ = value * 1;
                          $scope.gg = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
     $scope.pageEight = function(value){
         value_ = value * 1;
                          $scope.hh = value_;
                  $ionicSlideBoxDelegate.next();
   } ; 
 $scope.pageNine = function(value){
         value_ = value * 1;
                          $scope.ii = value_;
                  $ionicSlideBoxDelegate.next();
   } ; 
    $scope.pageTen = function(value){
         value_ = value * 1;
                          $scope.jj = value_;
                  $ionicSlideBoxDelegate.next();
   } ; 
    $scope.pageEleven = function(value){
         value_ = value * 1;
                          $scope.kk = value_;
                  $ionicSlideBoxDelegate.next();
   } ;
    
     $scope.pageTweleve = function(value){
        
         value_ = value * 1;
                          $scope.ll = value_;
                  $ionicSlideBoxDelegate.next();
            } ;
     $scope.pageThirteen= function(value){
        
         value_ = value * 1;
                          $scope.mm = value_;
                  $ionicSlideBoxDelegate.next();
            } ;
   $scope.lastPage = function  () {    
             $ionicLoading.show({
    content: 'Loading',
    animation: 'fade-in',
    showBackdrop: true,
    maxWidth: 200,
    showDelay: 0,
  });
         $timeout(function () {
              resul = $scope.aa + $scope.bb + $scope.cc + $scope.cc + $scope.dd + $scope.ee + $scope.ff + $scope.gg + $scope.hh + $scope.ii + $scope.jj + $scope.kk+$scope.ll+$scope.mm;
         $rootScope.result = resul;
         $state.go('Lastpage');
                           $ionicLoading.hide();
      }, 4000); 
       var alertPopup = $ionicPopup.alert({
       title: 'Result',
       template: 'The application took a look at your inputs and realised that in the next 10years,you might be worth,NGN {{result|number:2}}.keep the hustle.'
     });
     alertPopup.then(function(res) {
       console.log('Thank for using nipice');
     });
         };
});
