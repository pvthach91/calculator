var app = angular.module('app', []);
app.controller('logincontroller', function($scope, $http, $location, $window) {
    	$scope.submitForm = function(){
            var username = $scope.username;
            var password = $scope.password;
            var isValid = true;
            if (username == null || username.length == 0) {
                $scope.usernameError = "Please enter username";
                isValid = false;
            } else {
                $scope.usernameError = "";
            }
            if (password == null || password.length == 0) {
                $scope.passwordError = "Please enter password";
                isValid = false;
            } else {
                $scope.passwordError = "";
            }
            if (isValid) {
                var url = $location.absUrl();
                var config = {
                    headers : {
                        'Accept': 'application/json'
                    }
                }

                var data = {
                    username: $scope.username,
                    password: $scope.password
                };

                $http.post(url, data, config).then(function (response) {
                    var user = response.data.username;
                    $window.sessionStorage.setItem("user",user);
                    var url = $location.protocol() + "://" + location.host;
                    $window.location.href = url;
                }, function error(response) {
                    $scope.loginResultMessage = response.data.message;
                });
            }

	}
    $scope.loginResultMessage = "";

    $scope.signup = function(){
        var url = $location.protocol() + "://" + location.host + "/signup";
	    $window.location.href = url;
    }
});
