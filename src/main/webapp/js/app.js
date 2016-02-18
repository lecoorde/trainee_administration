'use strict';
angular.module('mainApp', ['ngRoute', 'traineeMod', 'locationMod', 'departmentMod', 'skillMod'])
    .config(function ($routeProvider) {
        $routeProvider

            .when('/trainees', {templateUrl: '../views/list/traineeList.html'})
            .when('/trainees/detail', {templateUrl: '../views/detail/traineeDetail.html'})

            .when('/locations', {templateUrl: '../views/list/locationList.html'})
            .when('/locations/detail', {templateUrl: '../views/detail/locationDetail.html'})

            .when('/departments', {templateUrl: '../views/list/departmentList.html'})
            .when('/departments/detail', {templateUrl: '../views/detail/departmentDetail.html'})

            .when('/skills', {templateUrl: '../views/list/skillList.html'})
            .when('/skills/detail', {templateUrl: '../views/detail/skillDetail.html'})

            .when('/about', {template: 'Made by Lennard Coordes and Denis Simon'})

            .otherwise({redirectTo: 'index.html'});
    });