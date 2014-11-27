package org.ioda.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

/**
 * This class performs sum called from JavaScript.
 */
public class IodaPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("tethering")) {
            this.tethering(callbackContext);
            return true;
        }
        return false;
    }

    private void tethering(CallbackContext callbackContext) {        
        Intent tetherSettings = new Intent();
        tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
        this.cordova.getActivity().startActivity(tetherSettings);
        callbackContext.success();
    }
}
