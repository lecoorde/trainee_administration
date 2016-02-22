'use strict';
angular.module('traineeMod', [])
    .factory('TraineeService', ['$http', '$q', function ($http, $q) {
        return {
            getTrainee: function (id) {
                return $http.get('http://localhost:7070/trainees/' + id).then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching trainee');
                    return $q.reject(errResponse);
                });

            },
            getSkills: function (id) {
                return $http.get('http://localhost:7070/trainees/skill_list/'+id).then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching skills');
                    return $q.reject(errResponse);
                });

            },
            getTrainees: function () {
                return $http.get('http://localhost:7070/trainees/list').then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching trainees');
                    return $q.reject(errResponse);
                });
            }
        }
    }])

    .controller('TraineeCtrl', ['$scope', 'TraineeService', function ($scope, TraineeService) {
        var self = this;
        self.trainees = [];
        self.traineeskills=[];
        self.trainee = {
            id: null,
            forename: '',
            lastName: '',
            jobName: '',
            birthday: '',
            start_of_training: '',
            departmentName: '',
            locationName: ''
        };

        self.getSkillsByTraineeId = function (id) {
            TraineeService.getSkills(id).then(
                function (s) {
                    self.traineeskills = s;
                },
                function (errResponse) {
                    console.error('Error while fetching skills');
                }
            );
        };
        self.fetchAllTrainees = function () {
            TraineeService.getTrainees().then(
                function (d) {
                    self.trainees = d;
                },
                function (errResponse) {
                    console.error('Error while fetching trainees');
                }
            );
        };
        self.getSingleTrainee = function (id) {
            TraineeService.getTrainee(id).then(function (d) {
                    self.trainee = d;
                },
                function (errResponse) {
                    console.error('Error while fetching trainee');
                }
            )
        };
        $scope.reset = function () {
            $scope.firstName = "TestVorname";
            $scope.lastName = "TestNachname";
            $scope.birthday = new Date(1996,10,30);
            $scope.start_of_training = new Date(2015,9,1);
            $scope.job = "Dualer Student Anwendungsentwicklung"
        };

        $scope.reset();
        self.fetchAllTrainees();
        self.getSkillsByTraineeId();
    }]);