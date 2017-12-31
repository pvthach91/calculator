var app = angular.module('app', []);
app.controller('historycontroller', function($scope, $http, $location, $window) {
    var user = $scope.name = $window.sessionStorage.getItem("user");
    if (user == null || user.length == 0){
        var url = $location.protocol() + "://" + location.host +"/login";
        $window.location.href = url;
    }

	$scope.refresh = function(){
        var url = $location.absUrl();
        var config = {
            headers : {
                'Accept': 'application/json'
            }
        }

        $http.post(url, user, config).then(function (response) {
            $scope.histories = response.data;
        }, function error(response) {
            $scope.historyResultMessage = response.data.message;
        });
	}

    $scope.calculate = function(){
        var url = $location.protocol() + "://" + location.host;
        $window.location.href = url;
    }

    $scope.logout = function(){
        $window.sessionStorage.removeItem("user")
        var url = $location.protocol() + "://" + location.host + "/login";
        $window.location.href = url;
    }

    $scope.refresh();
});
