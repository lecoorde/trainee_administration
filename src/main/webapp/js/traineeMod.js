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
            getSkillsByTraineeId: function (id) {
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

        $scope.trainee_id = "";
        $scope.trainee_lastName = "";
        $scope.trainee_forename = "";
        $scope.trainee_department = "";
        $scope.trainee_location = "";
        //$scope.filter_start_of_training = new Date(2015,8,1);
        //$scope.min_age = new Date(0);
        //$scope.max_age = new Date(9999,11,31);

        self.creatableTrainee={
            id:null
        };
        $scope.ageFilter = function (trainee) {
            return (trainee.birthday > $scope.min_age && trainee.birthday < $scope.max_age);
        };

        self.getSkillsByTraineeId = function (id) {
            TraineeService.getSkillsByTraineeId(id).then(
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
            self.createTrainee(self.creatableTrainee)
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
            self.creatableTrainee.forename = "TestVorname";
            self.creatableTrainee.lastName = "TestNachname";
            self.creatableTrainee.birthday = new Date(1996, 10, 30);
            self.creatableTrainee.start_of_training = new Date(2015, 9, 1);
        };

        self.reset();
        self.fetchAllTrainees();
    }]);