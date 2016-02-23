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
                return $http.get('http://localhost:7070/trainees/skill_list/' + id).then(function (response) {
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
            },
            createTrainee: function (trainee) {
                return $http.post('http://localhost:7070/trainees/createTrainee/', trainee)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while creating user');
                            return $q.reject(errResponse);
                        }
                    );
            }
        }
    }])

    .controller('TraineeCtrl', ['$scope', 'TraineeService', function ($scope, TraineeService) {
        var self = this;
        self.trainees = [];
        self.traineeskills = [];
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
        self.createableTrainee = {
            id: null,
            departmentId: ''
        }
        self.traineeDepartment={};
        self.traineeLocation={};

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
        self.createTrainee = function (trainee) {
            TraineeService.createTrainee(trainee)
                .then(
                    self.fetchAllTrainees(),
                    function (errResponse) {
                        console.error('Error while creating User.');
                    }
                );
        };
        self.submit = function () {
            self.createTrainee(self.createableTrainee)
        }
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
        self.reset = function () {
            self.createableTrainee.forename = "TestVorname";
            self.createableTrainee.lastName = "TestNachname";
            self.createableTrainee.birthday = new Date(1996, 10, 30);
            self.createableTrainee.start_of_training = new Date(2015, 9, 1);
        };
        self.setDepartmentId=function(id){
            self.createableTrainee.departmentId=id;
        }

        self.reset();
        self.fetchAllTrainees();
    }]);