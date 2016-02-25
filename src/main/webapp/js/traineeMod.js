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

                            console.error('Error while creating trainee');
                            return $q.reject(errResponse);
                        }
                    );
            },
            deleteTrainee: function (id) {
                return $http.post('http://localhost:7070/trainees/delete/' + id)
                    .then(function (response) {

                            return response.data;
                        }, function (errResponse) {

                            console.error('Error while deleting trainee');
                            return $q.reject(errResponse);
                        }
                    );
            },
            updateTrainee: function (trainee) {
                return $http.post('http://localhost:7070/trainees/updateTrainee/', trainee)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while updating Trainee');
                            return $q.reject(errResponse);
                        }
                    );
            }
        }
    }])

    .controller('TraineeCtrl', ['$scope', 'TraineeService', 'growl', function ($scope, TraineeService, growl) {
        var self = this;
        self.trainees = [];
        self.trainee = {
            id: null,
            forename: '',
            lastName: '',
            jobName: '',
            jobOrdinal:'',
            birthday: '',
            start_of_training: '',
            departmentName: '',
            departmentId:'',
            locationName: '',
            locationId:''
        };

        $scope.editingData = {};

        for (var i = 0, length = self.trainees.length; i < length; i++) {
            $scope.editingData[self.trainees[i].id] = false;
        }

        $scope.modify = function (trainee) {
            self.createableTrainee.forename = trainee.forename;
            self.createableTrainee.lastName = trainee.lastName;
            self.createableTrainee.birthday = new Date(trainee.birthday);
            self.createableTrainee.start_of_training = new Date(trainee.start_of_training);
            self.createableTrainee.jobOrdinal=trainee.jobOrdinal;
            self.createableTrainee.locationId=trainee.locationId;
            self.createableTrainee.departmentId=trainee.departmentId;
            self.createableTrainee.skillIds=trainee.skillIds;
            $scope.editingData[trainee.id] = true;
        };

        $scope.update = function (trainee, id) {
            self.createableTrainee.id = id;
            $scope.editingData[trainee.id] = false;
            TraineeService.updateTrainee(self.createableTrainee);

        };
        $scope.cancelUpdate = function (trainee) {
            $scope.editingData[trainee.id] = false;
        };

        $scope.trainee_id = "";
        $scope.trainee_lastName = "";
        $scope.trainee_forename = "";
        $scope.trainee_department = "";
        $scope.trainee_location = "";

        self.createableTrainee = {
            id: null
        };
        self.joblist=[
            {id:0,name:"Dualer Student - Anwendungsentwicklung"},
            {id:1,name:"Dualer Student - BWL"},
            {id:2,name:"Dualer Student - Systemintegration"},
            {id:3,name:"Fachinformatiker - Anwendungsentwicklung"},
            {id:4,name:"Fachinformatiker Systemintegration"},
            {id:5,name:"IT-Systemkaufmann"}
        ]


        self.submitTrainee = function () {
            TraineeService.createTrainee(self.createableTrainee).then(
                function () {
                    growl.success('Auszubildender wurde gespeichert.', {title: 'Erfolg'});
                },
                function () {
                    growl.error('Speichern fehlgeschlagen!', {title: 'Fehler!'});
                }
            )
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
        self.reset = function () {
            self.createableTrainee.birthday = new Date(1996, 10, 30);
            self.createableTrainee.start_of_training = new Date(2015, 9, 1);
        };
        self.confirmDelete = function confirmDelete(id) {

            if (confirm("Möchten Sie diesen Auszubildenden wirklich löschen?") == true) {
                TraineeService.deleteTrainee(id).then(
                    function () {
                        growl.success('Auszubildender wurde gelöscht.', {title: 'Erfolg'});
                    },
                    function () {
                        growl.error('Löschen fehlgeschlagen!', {title: 'Fehler!'});
                    }
                );
            }
        };

        self.reset();
        self.fetchAllTrainees();
    }]);