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
            }
        }
    }])

    .controller('DepartmentCtrl', ['$scope', 'DepartmentService', function ($scope, DepartmentService) {
        var self = this;
        self.departments = [];
        self.department = {id: null, name: '', description: ''};
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
        self.getSingleDepartment = function (id) {
            DepartmentService.getDepartment(id).then(function (d) {
                    self.department = d;
                },
                function (errResponse) {
                    console.error('Error while fetching department');
                }
            )
        };

        self.fetchAllDepartments();
    }]);
