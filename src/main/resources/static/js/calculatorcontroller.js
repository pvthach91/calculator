var app = angular.module('app', []);
app.controller('calculatorcontroller', function($scope, $http, $location, $window) {
    var user = $scope.name = $window.sessionStorage.getItem("user");
    if (user == null || user.length == 0){
        $window.location.href = 'login';
    }

	$scope.submitForm = function(){
	    var operation= $scope.operation;
        var firstNumber = $scope.firstNumber;
        var secondNumber = $scope.secondNumber;
        var isValid = true;
        var needCheckSecondNumber = true;
        var calculationRequest = "";
        var data = "";
        $scope.calculateResultMessage = "";

        if (firstNumber == null || firstNumber.length == 0 || isNaN(firstNumber)) {
            $scope.firstNumberError = "Please enter number";
            isValid = false;
        } else {
            $scope.firstNumberError = "";
        }

        if (operation == null || operation.length == 0) {
            $scope.operationError = "Please select operation";
            isValid = false;
        } else {
            $scope.operationError = "";
            if (operation == 'square'){
                calculationRequest = "/calculateOneParam";
                needCheckSecondNumber = false;
                data = {
                    user: user,
                    type: operation,
                    param: firstNumber
                };
            } else {
                calculationRequest = "/calculateTwoParam";
                data = {
                    user: user,
                    type: operation,
                    firstParam: firstNumber,
                    secondParam: secondNumber
                };
            }
        }
        if (needCheckSecondNumber){
            if (secondNumber == null || secondNumber.length == 0 || isNaN(secondNumber)) {
                $scope.secondNumberError = "Please enter number";
                isValid = false;
            } else {
                $scope.secondNumberError = "";
            }
        }

        if (isValid) {
            var url = $location.absUrl() + calculationRequest;
            var config = {
                headers : {
                    'Accept': 'application/json'
                }
            }

            $http.post(url, data, config).then(function (response) {
                $scope.calculateResultMessage = response.data;
            }, function error(response) {
                $scope.calculateResultMessage = response.data.message;
            });
        }
	}

    $scope.viewHistory = function(){
        $window.location.href = '/history';
    }

    $scope.logout = function(){
        $window.sessionStorage.removeItem("user")
        $window.location.href = '/login';
    }
});
