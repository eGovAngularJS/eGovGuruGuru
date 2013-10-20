﻿/**
* egovNgDashboard Module
*
* Description
*/
angular.module('egovNgDashboardService', ['egov.ui']).
provider('DashboardService', function(){
    this.$get = ['$http', function($http){
        // 통신 해더정보 설정
        var headers = {"Content-Type" : "application/json; charset=UTF-8"};

        // 서버 상태 정보 조회
        function status($scope){
            $http.get('state/getStateInfo.do', {headers : headers}).
            success(function(data, status){
                $scope.s1 = data.serverLoad;
                $scope.s2 = data.usedMem;
                $scope.s3 = data.processLoad;
                $scope.s4 = data.diskLoad;
                $scope.s5 = data.networkLoad;
            });
        };

        // data 가공
        function innerGetData(list){
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
        }

        // 서버 상태 정보 조회
        function chartData($scope){
            if($scope.selectType == "1") {
            	$scope.month = null;
            	$scope.day = null;
            }else if($scope.selectType == "2") {
            	$scope.day = null;
            }

            var params = {
                year : $scope.year,
                month : $scope.month,
                day : $scope.day   
            };

            // 기간에 따른 방문자 수
            $http.get('visit/retrieveVisitInfo.do', {params : params, headers : headers}).
            success(function(data, status){
                $scope.v1 = [
                    {"key": "unique", "values": innerGetData(data.unique), "area":true},
                    {"key": "visit" , "values": innerGetData(data.visit)}
                ];
            });

            // 지역별 방문자 수
            $http.get('visit/retrieveAreaInfo.do', {params : params, headers : headers}).
            success(function(data, status){
                $scope.visitListByLocaiton = data;
            });

            // 나이별 방문자 수
            $http.get('visit/retrieveAgeInfo.do', {params : params, headers : headers}).
            success(function(data, status){
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
            success(function(data, status){
                $scope.dSex = [
                    {key: "최초접속수(남성)", y: data.menUniqueCount},
                    {key: "최초접속수(여성)", y: data.womenUniqueCount}, 
                    {key: "총 접속수(남성)", y: data.menCount}, 
                    {key: "총 접속수(여성)", y: data.womenCount}
                ];
            });
        };

        function searchInfo($scope){
            // 조회 조건 조회
        	var str = "";
            if($scope.selectType == "1") {
                $scope.month = null;
                $scope.day = null;
                $scope.monthList = null;
                $scope.dayList = null;
            }else if($scope.selectType == "2") {
                $scope.day = null;
                $scope.month = null;
                $scope.monthList = null;
                $scope.dayList = null;
                str = "월";
            }else{
            	$scope.day = null;
            	$scope.dayList = null;
            	str = "일";
            }

            var params = {
                year : $scope.year,
                month : $scope.month,
                day : $scope.day    
            };
            
            function innerGetData2(list){
                var _list = [];
                var _obj, _pushObj;
                for(var i = 0, len = list.length; i < len; i++){
                    _obj = list[i];
                    _pushObj = {};
                    _pushObj.value = _obj;
                    _pushObj.name = _obj + str;
                    _list.push(_pushObj);
                }
                return _list;
            }
            //value.id as value.label group by value.group for value in myOptions
            $http.get('code/retrieveDateCodeList.do', {params:params, headers : headers}).
            success(function(data, status){
                console.log("======getSearchInfo",data);
                if($scope.selectType == "2"){
                	$scope.monthList = innerGetData2(data);
                }else if($scope.selectType == "3"){
                	$scope.dayList = innerGetData2(data);
                }
            });
        };

        // 

        return {
              status : status
            , chartData : chartData
            , searchInfo : searchInfo
        };
    }];
});
	


  