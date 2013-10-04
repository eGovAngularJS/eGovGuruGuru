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