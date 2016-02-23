'use strict';
angular.module('departmentMod', [])
    .factory('DepartmentService', ['$http', '$q', function ($http, $q) {
        return {
            getDepartment: function (id) {
                return $http.get('http://localhost:7070/departments/' + id).then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching department');
                    return $q.reject(errResponse);
                });

            },
            getDepartments: function () {
                return $http.get('http://localhost:7070/departments/list').then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching departments');
                    return $q.reject(errResponse);
                });
            },
            getTraineesByDepartmentId: function(id){
                return $http.get('http://localhost:7070/departments/trainee_list/' + id).then(function (response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching trainees');
                        return $q.reject(errResponse)
                    });
            },
            createDepartment: function(department){
                return $http.post('http://localhost:7070/trainees/createDepartment/', department)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while creating department');
                            return $q.reject(errResponse);
                        }
                    );
            }
        }
    }])

    .controller('DepartmentCtrl', ['$scope', 'DepartmentService', function ($scope, DepartmentService) {
        var self = this;
        self.departments = [];
        self.trainees=[];
        self.department = {id: null, name: '', description: ''};

        $scope.filter_department_id='';
        $scope.filter_department_name='';
        $scope.filter_department_description='';

        self.fetchAllDepartments = function () {
            DepartmentService.getDepartments().then(
                function (d) {
                    self.departments = d;
                },
                function (errResponse) {
                    console.error('Error while fetching departments');
                }
            );
        };
        self.createDepartment = function (department) {
            TraineeService.createTrainee(department)
                .then(
                    self.fetchAllDepartments(),
                    function (errResponse) {
                        console.error('Error while creating Department.');
                    }
                );
        };
        self.getSingleDepartment = function (id) {
            DepartmentService.getDepartment(id).then(function (d) {
                    self.department = d;
                },
                function (errResponse) {
                    console.error('Error while fetching department');
                }
            )
        };
        self.getTraineesByDepartmentId = function (id) {
            DepartmentService.getTraineesByDepartmentId(id).then(function (d) {
                    self.trainees = d;
                },
                function (errResponse) {
                    console.error('Error while fetching trainees')
                }
            )
        };
        self.reset=function(){
            self.department.name="Bitte Namen eingeben";
            self.department.description="Bitte Beschreibung eingeben"
        };
        self.submit=function(){
            DepartmentService.createDepartment(self.department);
        };
        self.reset();
        self.fetchAllDepartments();
    }]);
