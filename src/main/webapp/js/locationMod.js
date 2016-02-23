'use strict';
angular.module('locationMod', [])
    .factory('LocationService', ['$http', '$q', function ($http, $q) {
        return {
            getLocation: function (id) {
                return $http.get('http://localhost:7070/locations/' + id).then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching location');
                    return $q.reject(errResponse);
                });

            },
            getLocations: function () {
                return $http.get('http://localhost:7070/locations/list').then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching locations');
                    return $q.reject(errResponse);
                });
            },
            getTraineesByLocationId: function(id){
                return $http.get('http://localhost:7070/locations/trainee_list/' + id).then(function(response){
                    return response.data;
                }, function (errResponse){
                    console.error('Error while fetching trainees')
                });
            }
        }
    }])

    .controller('LocationCtrl', ['$scope', 'LocationService', function ($scope, LocationService) {
        var self = this;
        self.locations = [];
        self.trainees = [];
        self.location = {id: null, name: '', street: '', houseNum: '', postCode: '', city: ''};

        $scope.filter_location_id='';
        $scope.filter_location_name='';
        $scope.filter_location_street='';
        $scope.filter_location_postCode='';
        $scope.filter_location_city='';

        self.fetchAllLocations = function () {
            LocationService.getLocations().then(
                function (d) {
                    self.locations = d;
                },
                function (errResponse) {
                    console.error('Error while fetching locations');
                }
            );
        };
        self.getTraineesByLocationId = function(id){
            LocationService.getTraineesByLocationId(id).then(
                function(d){
                    self.trainees = d;
                },
                function(errResponse){
                    console.error('Error while fetching trainees');
                }
            );
        };
        self.getSingleLocation = function (id) {
            LocationService.getLocation(id).then(function (d) {
                    self.location = d;
                },
                function (errResponse) {
                    console.error('Error while fetching location');
                }
            )
        };
        self.fetchAllLocations();
    }]);
