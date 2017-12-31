var app = angular.module('app', []);
app.controller('signupcontroller', function($scope, $http, $location, $window) {
	$scope.submitForm = function(){
        $scope.signupResultMessage = "";
        var username = $scope.username;
        var password = $scope.password;
        var repeatPassword = $scope.repeatPassword;
        var isValid = true;
        if (username == null || username.length < 3) {
            $scope.usernameError = "Please enter username at least 3 characters";
            isValid = false;
        } else {
            $scope.usernameError = "";
        }
        if (password == null || password.length < 4) {
            $scope.passwordError = "Please enter password at least 4 characters";
            isValid = false;
        } else {
            $scope.passwordError = "";
        }
        if (repeatPassword == null ||repeatPassword.length < 4) {
            $scope.repeatPasswordError = "Please enter password at least 4 characters";
            isValid = false;
        } else {
            $scope.repeatPasswordError = "";
        }
        if (isValid) {
            if (password!= repeatPassword) {
                $scope.repeatPasswordError = "Password doesn't mach";
                isValid = false;
            } else {
                $scope.repeatPasswordError = "";
            }
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
                $scope.signupResultMessage = response.data.message;
            });
        }

	}

    $scope.login = function(){
        var url = $location.protocol() + "://" + location.host + "/login";
	    $window.location.href = url;
    }
});

