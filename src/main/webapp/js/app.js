'use strict';
angular.module('mainApp', ['ngRoute', 'traineeMod', 'locationMod', 'departmentMod', 'skillMod', 'angular-growl', 'ngAnimate'])
    .config(function ($routeProvider) {
        $routeProvider

            .when('/trainees', {templateUrl: '../views/list/traineeList.html'})
            .when('/trainees/form', {templateUrl: '../views/form/addTrainee.html'})
            .when('/trainees/update', {templateUrl: '../views/form/updateTrainee.html'})

            .when('/locations', {templateUrl: '../views/list/locationList.html'})
            .when('/locations/form', {templateUrl: '../views/form/addLocation.html'})
            .when('/locations/update', {templateUrl: '../views/form/updateLocation.html'})


            .when('/departments', {templateUrl: '../views/list/departmentList.html'})
            .when('/departments/form', {templateUrl: '../views/form/addDepartment.html'})
            .when('/departments/update', {templateUrl: '../views/form/updateDepartment.html'})


            .when('/skills', {templateUrl: '../views/list/skillList.html'})
            .when('/skills/form', {templateUrl: '../views/form/addSkill.html'})
            .when('/skills/update', {templateUrl: '../views/form/updateSkill.html'})


            .when('/about', {template: 'Made by Lennard Coordes and Denis Simon'})

            .otherwise({redirectTo: '/trainees'});
    }).config(['growlProvider', function (growlProvider) {
    growlProvider.onlyUniqueMessages(false);
    growlProvider.globalTimeToLive({success: 3000, error: -1, warning: -1, info: 4000});
    growlProvider.globalReversedOrder(true);
    growlProvider.globalDisableCountDown(true);
}]);
