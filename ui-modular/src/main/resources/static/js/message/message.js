angular.module('message', ['smart-table']).controller('message', function($scope, $http) {
	$http.get('/resource/').success(function(data) {
		$scope.greeting = data;
	});
	$http.get('/resource/users').success(function(data) {
		$scope.users = data;
	});

	$scope.removeRow = function removeRow(row) {
		$http.delete('/resource/users/' + row.id)
			.then(function(res){
				var index = $scope.users.indexOf(row);
				if (index !== -1) {
					$scope.users.splice(index, 1);
				}
			}, function(res){
				alert(res.data.error);
			});

	}
});
