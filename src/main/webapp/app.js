'use strict';
angular.module('tutorialApp', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'start.html'})
            .when('/about', {template: 'Über die Trainees'})
            .when('/detail', {templateUrl:'traineeDetail.html'})
            .otherwise({redirectTo: '/'});
    })
    .factory('TraineeService', ['$http', '$q', function ($http, $q) {
        return {
            getTrainee: function (id) {
               return $http.get('http://localhost:7070/trainee/' + id).then(function (response) {
                    return response.data;
                },function (errResponse) {
                   console.error('Error while fetching users');
                   return $q.reject(errResponse);
               });

            },
            getTrainees: function () {
             return   $http.get('http://localhost:7070/list').then(function (response) {
                        return response.data;
                    }, function (errResponse) {
                        console.error('Error while fetching users');
                        return $q.reject(errResponse);
                    });
            }
        }
    }])
    .controller('TraineeCtrl', function ($scope, $http) {
        $http.get('http://localhost:7070/list').then(function (response) {
            $scope.trainees = response.data;
        });

    })
    .controller('OneTraineeCtrl', ['$scope', 'TraineeService', function ($scope, TraineeService) {
        var self = this;
        self.trainees = [];
        self.trainee={id:null,forename:'',lastName:'',job:'',birthday:1,start_of_training:1};
        self.fetchAllTrainees = function () {
            TraineeService.getTrainees().then(
                function (d) {
                    self.trainees = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Currencies');
                }
            );
        };
        self.getSingleTrainee=function(id){
            TraineeService.getTrainee(id).then(function(d){
                self.trainee=d;
            },

                function (errResponse) {
                    console.error('Error while fetching Currencies');
                }
            )
        }
        self.fetchAllTrainees();
    }]);
