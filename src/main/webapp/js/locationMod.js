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
            }
        }
    }])

    .controller('LocationCtrl', ['$scope', 'LocationService', function ($scope, LocationService) {
        var self = this;
        self.locations = [];
        self.location = {id: null, name: '', street: '', houseNum: '', postCode: 1, city: ''};
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
