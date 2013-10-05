/**
* egovNgDashboard Module
*
* Description
*/
angular.module('egovNgDashboard', ['egov.ui']).
	config(['egovGridProvider', function (egovGridProvider) {
		// egovGridProvider.setOption({
							// rowHeight: 36,
							// headerRowHeight: 36
		// });
	}]).
	controller('mainCtrl', ['$scope', function ($scope) {
		$scope.userList = [];

		for (var i = 0; i < 10; i++) {
			$scope.userList.push({
				id : i,
				name : "jeado"+i,
				email : "haibane"+i+"@gmail.com",
				regdate : "20130910"
			});
		}

		$scope.year = "2013";
    $scope.month = "2";
    $scope.day = "3";
    $scope.selectType = "1";

    // chart data 생성
    function getData(){
        $scope.v1 = [
            {"key": "Series 1", "values":  [ [1, 115], [2, 117], [3, 110], [4, 118], [5, 115], [6, 14] ] },
            {"key": "Series 2", "values":  [ [1, 115], [2, 110], [3, 112], [4, 118], [5, 115], [6, 114] ] }
        ];
        $scope.s1 = [
            {key: "One", y: 58},
            {key: "Two", y: 42 }
        ];

        $scope.percent1 = "58";
        $scope.percent2 = "43";
        $scope.percent3 = "91";


        $scope.dAge = [
            {key: "10이하", y: 62},
            {key: "10대", y: 1522},
            {key: "20대", y: 3540},
            {key: "30대", y: 2813},
            {key: "40대", y: 988},
            {key: "50대", y: 120 },
            {key: "50이상", y: 30 }
        ];

        $scope.dSex = [
            {key: "남자", y: 152},
            {key: "여자", y: 58}
        ];
        
    };

    // 년도별 정보 조회
    $scope.searchData = function(){
        if($scope.selectType == '1') {
            // 년 조회 -> 해당 연도 12 개월 정보 조회 
            $scope.v1 = [
                {"key": "Series 1", "values":  [ [1, 115], [2, 117], [3, 110], [4, 118], [5, 115], [6, 14] ] },
                {"key": "Series 2", "values":  [ [1, 115], [2, 110], [3, 112], [4, 118], [5, 115], [6, 114] ] }
            ];
        }else if($scope.selectType == '2'){
            // 월 조회 -> 해당 월 일 정보 조회
            $scope.v1 = [
                {"key": "Series 1", "values":  [ [1, 51], [2, 71], [3, 101], [4, 81], [5, 151], [6, 41] ] },
                {"key": "Series 2", "values":  [ [1, 151], [2, 101], [3, 121], [4, 181], [5, 51], [6, 141] ] }
            ];
        }else{
            // 일 조회 -> 해당 일의 시간별 정보 조회
            $scope.v1 = [
                {"key": "Series 1", "values":  [ [1, 2], [2, 4], [3, 5], [4, 4], [5, 2], [6, 4] ] },
                {"key": "Series 2", "values":  [ [1, 3], [2, 4], [3, 6], [4, 1], [5, 5], [6, 8] ] }
            ];
        }
    }


    // chart axis 설정
    $scope.xFunction = function(){
        return function(d){
            return d.key ;
        }
    }
    $scope.yFunction = function(){
        return function(d){
            return d.y;
        }
    }

    $scope.xFunctionType = function(){
        return function(d){
            var str = "시";
            if($scope.selectType == '1') {
                str = "월";
            }else if($scope.selectType == '2') {
                str = "일";
            }
            return d + str;
        }
    }

    // 차트 툴팁 생성
    $scope.toolTipContentFunction = function(){
        return function(key, x, y, e, graph) {
            console.log(key, x, y, e, graph);
            return  'Super New Tooltip' +
                '<h1>' + key + '</h1>' +
                '<p>' +  y + ' at ' + x + '</p>';
        }
    }
    
    // 초기화 - data 처리
    getData();

	}]).
	directive('widget', ['$compile',function ($compile) {
		return {
			restrict: 'EA',
			// transclude: true,
			// replace: true,
			scope: true,
			template: function (i, tEl) {
				var template = '<section class="widget"> \
															     	<header> \
															        <h4> \
															         <i class="icon-group"></i> \
															         <small> \
															             테스트 \
															         </small> \
															        </h4> \
															      </header> \
															      <div class="body">'+tEl+
															      '</div> \
															     </section>';
				return template;
			},
			compile: function(tElement, tAttrs){
				jQuery(tElement).find('h4').html('<i class="'+(tAttrs.icon || "")+'"></i> '+(tAttrs.title || "")+' <small>'+(tAttrs.smallTitle || "")+'</small>');
				console.log(tElement);
				return function linking($scope, iElm, iAttrs, controller){
					
				};
			}
		};
		// 	link: function (scope, iElement, iAttrs) {

		// 		var template = '<section class="widget"> \
		// 													     	<header> \
		// 													        <h4> \
		// 													         <i class="icon-group"></i> \
		// 													         {{title}} \
		// 													         <small> \
		// 													             테스트 \
		// 													         </small> \
		// 													        </h4> \
		// 													      </header> \
		// 													      <div class="body no-margin"> \
		// 													      </div> \
		// 													     </section>';
		// 		iElement.append(angular.element(template));					     
		// 		console.log(iElement);
		// 		// $compile(iElement)(scope);
		// 	}
		// };
	}]);