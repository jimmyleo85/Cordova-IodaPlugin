	window.tethering = function(successCallback, errorCallback) {
	    cordova.exec(successCallback, errorCallback, "IodaPlugin", "tethering", []);
	};