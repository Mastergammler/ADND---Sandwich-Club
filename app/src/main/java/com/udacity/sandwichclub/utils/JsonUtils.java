package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class JsonUtils {

    //---------------
    //  CONSTANTS
    //---------------

    public static final String NAME_OBJ = "name";
    public static final String NAME_MAIN_ATTR = "mainName";
    public static final String NAME_KNOWN_AS_ARRAY = "alsoKnownAs";
    public static final String ORIGIN_ATTR = "placeOfOrigin";
    public static final String DESCRIPTION_ATTR = "description";
    public static final String IMAGE_ATTR = "image";
    public static final String INGREDIENTS_ARRAY = "ingredients";

    //--------------
    //  FUNCTIONS
    //--------------

    public static Sandwich parseSandwichJson(String json) throws JSONException
    {
        JSONObject jsonMain = new JSONObject(json);
        JSONObject nameObj = jsonMain.getJSONObject(NAME_OBJ);

        String mainName = getJsonValue(nameObj,NAME_MAIN_ATTR);
        String[] knownNames = getJsonArrayValues(nameObj,NAME_KNOWN_AS_ARRAY);

        String origin = getJsonValue(jsonMain,ORIGIN_ATTR);
        String desc = getJsonValue(jsonMain,DESCRIPTION_ATTR);
        String imageUrl = getJsonValue(jsonMain,IMAGE_ATTR);
        String[] ingredients = getJsonArrayValues(jsonMain,INGREDIENTS_ARRAY);

       return new Sandwich(mainName, Arrays.asList(knownNames), origin, desc, imageUrl, Arrays.asList(ingredients));
    }

    private static String getJsonValue(JSONObject jsonObj,String attrName) throws JSONException
    {
        return jsonObj.getString(attrName);
    }

    private static String[] getJsonArrayValues(JSONObject jsonObject, String arrayName) throws JSONException
    {
        JSONArray jsonArray = jsonObject.getJSONArray(arrayName);
        String[] values = new String[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++)
        {
            values[i] = jsonArray.getString(i);
        }

        return values;
    }


}
