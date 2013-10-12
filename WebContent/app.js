/**
* egovNgDashboard Module
*
* Description
*/
angular.module('egovNgDashboard', ['egov.ui']).
	config(['egovGridFormatterProvider', function (egovGridFormatterProvider) {
		egovGridFormatterProvider.setFormatatter("waitting",function (row, cell, value, columnDef, dataContext) {
    	return "기달려주세요..";
    });

		egovGridFormatterProvider.setFormatatter("percent",function (row, cell, value, columnDef, dataContext) {
    	return value+"%";
    });

    egovGridFormatterProvider.setFormatatter("change",function (row, cell, value, columnDef, dataContext) {
    	var text = Math.abs(value),
    			css = "icon-caret-down color-red", 
    			css = "icon-caret-down color-red"; 

    	if(value > 0){
    		css = "icon-caret-up color-green";
    	}

    	return "<div class='value'><i class='"+css+"'></i> "+text+"</div>";
    });
	}]).
	controller('mainCtrl', ['$scope','$http', function ($scope, $http) {
		// 그리드 샘플 데이터
    $scope.visitListByLocaiton = [];
    
    $scope.renderSparkline = function(cellNode, row, dataContext, colDef) {
      jQuery(cellNode).empty().sparkline(dataContext.period, { width: "100%", type: "line", fillColor : "", lineColor: '#56bc76' });
    };

    // 차트 샘플 데이터 
    // 년도별 정보 조회
    $scope.getData = function(){
        $scope.s1 = [
            {key: "One", y: 58},
            {key: "Two", y: 42 }
        ];
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

        $scope.percent1 = "58";
        $scope.percent2 = "43";
        $scope.percent3 = "91";
        
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
    };

    // 초기값 설정
    $scope.year = "2013";
    $scope.month = null;
    $scope.day = null;
    $scope.selectType = "1";

    // data 조회
    $scope.searchData = function(year, month, day){
    	
    	var headers = {"Content-Type" : "application/json; charset=UTF-8"};
    	
    	if($scope.selectType == '1') {
    		$scope.month = null;
    		$scope.day = null;
    	}else if($scope.selectType == '2') {
    		$scope.day = null;
    	}
    	
    	var params = {
				year : year,
        month : month,
        day : day	
    	};
    	
      // 기간에 따른 방문자 수
      $http.get('visit/retrieveVisitInfo.do', {params : params, headers : headers}).
      success(function(data, status, headers, config){
          console.log("1",data);
          var innerGetData = function(list){
          	var _list = [];
          	var _obj, _o, _pushObj;
          	for(var i = 0, len = list.length; i < len; i++){
          		_obj = list[i];
          		_pushObj = [];
          		for(_o in _obj){
          			_pushObj.push(""+_o);
          			_pushObj.push(_obj[_o]);
          		}
          		_list.push(_pushObj);
          	}
          	return _list;
          };
          $scope.v1 = [
                {"key": "unique", "values": innerGetData(data.unique)},
                {"key": "visit"	, "values": innerGetData(data.visit)}
            ];
      });
      
      // 지역별 방문자 수
      $http.get('visit/retrieveAreaInfo.do', {params : params, headers : headers}).
      success(function(data, status, headers, config){
          console.log("2",data);
          $scope.visitListByLocaiton = data;
      });
      
      // 나이별 방문자 수
      $http.get('visit/retrieveAgeInfo.do', {params : params, headers : headers}).
      success(function(data, status, headers, config){
          console.log("3",data);
          $scope.dAge = [
	            {key: "10이하", y: data.a0},
	            {key: "10대", y: data.a1},
	            {key: "20대", y: data.a2},
	            {key: "30대", y: data.a3},
	            {key: "40대", y: data.a4},
	            {key: "50대", y: data.a5 }
	        ];
      });
      
      // 성별 방문자 수
      $http.get('visit/retrieveGenderInfo.do', {params : params, headers : headers}).
      success(function(data, status, headers, config){
          console.log("4",data);
          $scope.dSex = [
  	            {key: "최초접속수(남성)", y: data.menUniqueCount},
  	            {key: "최초접속수(여성)", y: data.womenUniqueCount}, 
    	        {key: "총 접속수(남성)", y: data.menCount}, 
    	        {key: "총 접속수(여성)", y: data.womenCount}
  	        ];
      });
  	};
  
	  // chart axis 설정
	  $scope.xFunction = function(){
	      return function(d){
	          return d.key ;
	      };
	  };
	  $scope.yFunction = function(){
	      return function(d){
	          return d.y;
	      };
	  };

	  $scope.xFunctionType = function(){
	      return function(d){
	          var str = "시";
	          if($scope.selectType == '1') {
	              str = "월";
	          }else if($scope.selectType == '2') {
	              str = "일";
	          }
	          return d + str;
	      };
	  };

	  // 차트 툴팁 생성
	  $scope.toolTipContentFunction = function(){
	      return function(key, x, y, e, graph) {
	          console.log(key, x, y, e, graph);
	          return  'Super New Tooltip' +
	              '<h1>' + key + '</h1>' +
	              '<p>' +  y + ' at ' + x + '</p>';
	      };
	  };
	  
	  // 초기화 - data 처리
	  $scope.searchData();

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
  						        </h4> \
  						      </header> \
  						      <div class="body">'+tEl+
  						      '</div> \
  						     </section>';
			return template;
		},
		compile: function(tElement, tAttrs){
			jQuery(tElement).find('h4').html('<i class="'+(tAttrs.icon || "")+'"></i> '+(tAttrs.title || "")+' <small>'+(tAttrs.smallTitle || "")+'</small>');
			return function linking($scope, iElm, iAttrs, controller){
				
			};
		}
	};
}]);