package jiacc.myutilslib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiacc on 2018/1/29.
 */

public class JSONUtils {

    public static boolean isPrintException = false;
    private static boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }
    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || JSONUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static String getString(String jsonData, String key, String defaultValue) {
        if (JSONUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj) {
        if (sourceObj == null) {
            return null;
        }

        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        for (Iterator<?> iter = sourceObj.keys(); iter.hasNext();) {
            String key = (String) iter.next();
            keyAndValueMap.put(key, getString(sourceObj, key, ""));
        }
        return keyAndValueMap;
    }

    public static Map<String, String> parseKeyAndValueToMap(String source) {
        if (JSONUtils.isEmpty(source)) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(source);
            return parseKeyAndValueToMap(jsonObject);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if (jsonObject == null || JSONUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if (JSONUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject, key, (Integer)defaultValue);
    }

    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData, key, (Integer)defaultValue);
    }
    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if (jsonObject == null || JSONUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if (JSONUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    //

    public static String JSONToString(JSONObject obj) {
        if (obj == null) return "";
        return obj.toString();
    }

    public static JSONObject createJSON(Map<String, String> map) {
        if (map == null || map.isEmpty()) return null;

        JSONObject obj = new JSONObject();
        try {

            Set<String> keys = map.keySet();
            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                String val = map.get(key);
                obj.put(key, val);
            }

        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static JSONObject createJSON(String key, String val) {
        if (JSONUtils.isEmpty(key) || JSONUtils.isEmpty(val)) return null;

        JSONObject obj = new JSONObject();
        try {
            obj.put(key, val);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static String createJSONString(Map<String, String> map) {
        return JSONToString(createJSON(map));
    }



    public static String createJSONString(String key, String val) {
        return JSONToString(createJSON(key, val));
    }

    //

    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        if (jsonObject == null || JSONUtils.isEmpty(key)) {
            return null;
        }

        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            jsonArray = null;
            if (isPrintException) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }


    public static String mergeJSONString(String json1, String json2) {
        Map<String, String> map = new HashMap<String, String>();
        if (!JSONUtils.isEmpty(json1)) {
            Map<String, String> map1 = parseKeyAndValueToMap(json1);
            if (map1 != null)
                map.putAll(map1);
        }

        if (!JSONUtils.isEmpty(json2)) {
            Map<String, String> map2 = parseKeyAndValueToMap(json2);
            if (map2 != null)
                map.putAll(map2);
        }

        return createJSONString(map);
    }
}
