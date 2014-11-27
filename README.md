Cordova Plugin Example
======================

This is sample cordova plugin for tethering (currently for android platform only). Tested with Cordova 3.4 + Android 4.2.2

How to run example?
-------------------

To add this plugin to your cordova project, type:

	cordova plugin add https://github.com/jimmyleo85/IodaPlugin.git

And include IodaPlugin.js in your www/index.html  

	<script type="text/javascript" src="plugins/IodaPlugin.js"></script>

This will expose example function `tethering` to your cordova box. Function receives two integers and returns their tethering.
**Pretty useless, but it demonstrates how to connect your javascript code with native layer - tethering is calculated by native code outside of cordova box.**

To call `tethering` from javascript code write:

	window.tethering(function(result) { alert("Result:" + result); }, function(err) { alert(err); });

First two parameters are numbers to tethering, then two functions: success callback and error callback. In this example, page shuld alert "Result: 5". 

Here is full `www/index.html`:

	<!DOCTYPE html>
	<html>
		<head>
			<meta charset="utf-8" />
			<title>Example</title>

			<script type="text/javascript" src="cordova.js"></script>
			<script type="text/javascript" src="plugins/IodaPlugin.js"></script>

			<script type="text/javascript">
				function onLoad() {
					document.addEventListener('deviceready', onDeviceReady, false);
				}

				function onDeviceReady() {
					window.tethering(function(result) { alert("Result:" + result); }, function(err) { alert(err); });
				}
			</script>
		</head>

		<body onload="onLoad()">
			<h1>Sample plugin</h1>
		</body>
	</html>

IodaPlugin.js
---------------

	window.tethering = function(num1, num2, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "IodaPlugin", "tethering", []);
	};

In this file we extend `window` object with function `tethering` which calls `cordova.exec`. Cordova translates that and calls `execute` method from our class declared in IodaPlugin.java. 

Arguments to `exec` are allways the same: two callback functions (success and error callback), plugin class name, action name and array of arguments.
You can pass any number of arguments - they are processed by execute function as described.
