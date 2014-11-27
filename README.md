Cordova Plugin Example
======================

This is sample cordova plugin for tethering (currently for android platform only). Tested with Cordova 3.4 + Android 4.2.2

How to run example?
-------------------

To add this plugin to your cordova project, type:

	cordova plugin add https://github.com/jimmyleo85/Cordova-IodaPlugin.git

And include IodaPlugin.js in your www/index.html  

	<script type="text/javascript" src="plugins/IodaPlugin.js"></script>

This will expose example function `tethering` to your cordova box. Function receives two integers and returns their tethering.
**Pretty useless, but it demonstrates how to connect your javascript code with native layer - tethering is calculated by native code outside of cordova box.**

To call `tethering` from javascript code write:

	window.tethering(function(result) { alert("Result:" + result); }, function(err) { alert(err); });

First two parameters are numbers to tethering, then two functions: success callback and error callback. In this example, link should open intent setting Android (Tethering). 

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
			<h1>Ioda plugin</h1>
		</body>
	</html>

IodaPlugin.js
---------------

	window.tethering = function(successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "IodaPlugin", "tethering", []);
	};

In this file we extend `window` object with function `tethering` which calls `cordova.exec`. Cordova translates that and calls `execute` method from our class declared in IodaPlugin.java. 

Arguments to `exec` are allways the same: two callback functions (success and error callback), plugin class name, action name and array of arguments.
You can pass any number of arguments - they are processed by execute function as described.

plugin.xml
----------

	<?xml version="1.0" encoding="UTF-8"?>
	<plugin xmlns="http://www.phonegap.com/ns/plugins/1.0"
		id="org.ioda.plugin.IodaPlugin"
		version="1.0.0">

		<name>IodaPlugin</name>

		<description>
		Cordova plugin example
		</description>

		<asset src="www/IodaPlugin.js" target="plugins/IodaPlugin.js" />

		<engines>
			<engine name="cordova" version=">=3.0.0" />
		</engines>

		<!-- android -->
		<platform name="android">
			<config-file target="res/xml/config.xml" parent="/*">
				<feature name="IodaPlugin">
					<param name="android-package" value="org.ioda.plugin.IodaPlugin"/>
				</feature>
			</config-file>

			<source-file src="src/android/org/ioda/plugin/IodaPlugin.java" target-dir="src/org/ioda/plugin" />
		</platform>

		<!-- more platforms here -->

	</plugin>

This file tells cordova how to deal with plugin. Package name, class name and (relative) paths to files are defined here. More details about plugin.xml [here](http://docs.phonegap.com/en/3.4.0/plugin_ref_spec.md.html#Plugin%20Specification).

For more details refer:

[Cordova Plugin Development Guide](http://docs.phonegap.com/en/3.4.0/guide_hybrid_plugins_index.md.html#Plugin%20Development%20Guide)
<br />
and
<br />
[Android Plugins](http://docs.phonegap.com/en/3.4.0/guide_platforms_android_plugin.md.html#Android%20Plugins)
<br />
<br />
That's it :)