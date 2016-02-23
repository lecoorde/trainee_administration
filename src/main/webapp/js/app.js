'use strict';
angular.module('mainApp', ['ngRoute', 'traineeMod', 'locationMod', 'departmentMod', 'skillMod'])
    .config(function ($routeProvider) {
        $routeProvider

            .when('/trainees', {templateUrl: '../views/list/traineeList.html'})
            .when('/trainees/form', {templateUrl: '../views/form/addTrainee.html'})

            .when('/locations', {templateUrl: '../views/list/locationList.html'})
            .when('/locations/detail', {templateUrl: '../views/detail/locationDetail.html'})
            .when('/locations/form', {templateUrl: '../views/form/addLocation.html'})


            .when('/departments', {templateUrl: '../views/list/departmentList.html'})
            .when('/departments/detail', {templateUrl: '../views/detail/departmentDetail.html'})
            .when('/departments/form', {templateUrl: '../views/form/addDepartment.html'})


            .when('/skills', {templateUrl: '../views/list/skillList.html'})
            .when('/skills/detail', {templateUrl: '../views/detail/skillDetail.html'})
            .when('/skills/form', {templateUrl: '../views/form/addSkill.html'})


            .when('/about', {template: 'Made by Lennard Coordes and Denis Simon'})

            .otherwise({redirectTo: '/trainees'});
    });
