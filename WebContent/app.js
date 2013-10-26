/**
* egovNgDashboard Module
*
* Description
*/
angular.module('egovNgDashboard', ['egov.ui', 'egovNgDashboardService']).
config(['egovGridFormatterProvider', function (egovGridFormatterProvider) {
	egovGridFormatterProvider.setFormatatter("waitting",function (row, cell, value, columnDef, dataContext) {
		return "로딩중입니다.";
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
controller('mainCtrl', ['$scope','$window','DashboardService', function ($scope,$window,DashboardService) {
	// 그리드 샘플 데이터
	$scope.visitListByLocaiton = [];

	$scope.renderSparkline = function(cellNode, row, dataContext, colDef) {
	  jQuery(cellNode).empty().sparkline(dataContext.period, { width: "100%", type: "line", fillColor : "", lineColor: '#56bc76' });
	};

	// 초기값 설정
	$scope.yearList = [{value:"2011", name:"2011년"}, {value:"2012", name:"2012년"}, {value:"2013", name:"2013년"}];
	$scope.monthList;
	$scope.dayList;
	$scope.year = "2013";
	$scope.month = null;
	$scope.day = null;
	$scope.selectType = "1";
	
	$scope.s1 = 0;
	$scope.s2 = 0;
	$scope.s3 = 0;
	$scope.s4 = 0;
	$scope.s5 = 0;

	// type 변경
	$scope.changeType = function(type) {
		$scope.selectType = type;
		$scope.getSearchInfo();
	};
	
	// chart data 조회
	$scope.searchData = function(selectType, year, month, day){
		console.log(selectType, year, month, day);
	    DashboardService.chartData($scope, selectType, year, month, day);
	};

	// 차트 색상
	$scope.visitsColor = function(){
		return ["#eac85e", "#cf6d51"];
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
			return  '<h4>' + key +' : '+ y.value + '</h4>';
		};
	};
	  
	$scope.getSearchInfo = function(type){
		DashboardService.searchInfo($scope, type);
	};
  
	$scope.tabActive = function(){
		$scope.$broadcast("resize");
	};
	// 초기화 - data 처리
  
  // 5초마다 조회
	setInterval(function(){
		DashboardService.status($scope);
	}, 5000);
	DashboardService.status($scope);

    // 기본 방문 정보 조회
	$scope.searchData();

}]).
directive('widget', ['$compile',function ($compile) {
	return {
		restrict: 'EA',
		// transclude: true,
		// replace: true,
		scope: false,
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