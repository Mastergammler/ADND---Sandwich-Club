package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        JSONObject sandwichContainer = new JSONObject(json);

        JSONObject nameObj = sandwichContainer.getJSONObject(NAME_OBJ);
        String mainName = nameObj.getString(NAME_MAIN_ATTR);

        JSONArray knownAs = nameObj.getJSONArray(NAME_KNOWN_AS_ARRAY);
        String[] knownNames = new String[knownAs.length()];
        for(int i=0; i < knownAs.length(); i++)
        {
            knownNames[i] = knownAs.getString(i);
        }

        String origin = sandwichContainer.getString(ORIGIN_ATTR);
        String descr = sandwichContainer.getString(DESCRIPTION_ATTR);
        String imageUrl = sandwichContainer.getString(IMAGE_ATTR);
        JSONArray ingredients = sandwichContainer.getJSONArray(INGREDIENTS_ARRAY);
        String[] ingredientsArray = new String[ingredients.length()];
        for(int i =0; i < ingredients.length(); i++)
        {
            ingredientsArray[i] = ingredients.getString(i);
        }

       return new Sandwich(mainName, Arrays.asList(knownNames), origin, descr, imageUrl, Arrays.asList(ingredientsArray));
    }


}
