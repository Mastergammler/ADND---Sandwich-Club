package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private static final int TV_LABEL_CHILD_INDEX = 0;
    private static final int TV_CONTENT_CHILD_INDEX = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        intiTvLabels();

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try
        {
            sandwich = JsonUtils.parseSandwichJson(json);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        setTvContent(R.id.tc_origin,sandwich.getPlaceOfOrigin(),false);
        setTvContent(R.id.tc_known_as,sandwich.getAlsoKnownAs().toString(),false);
        setTvContent(R.id.tc_ingredients,sandwich.getIngredients().toString(),false);
        setTvContent(R.id.tc_description,sandwich.getDescription(),false);
    }

    private void setTvContent(int parentId,String text,boolean label)
    {
        int childIndex = label ? TV_LABEL_CHILD_INDEX : TV_CONTENT_CHILD_INDEX;

        FrameLayout parent = findViewById(parentId);
        TextView contentView = (TextView) parent.getChildAt(childIndex);
        contentView.setText(text);
    }

    private void intiTvLabels()
    {
        setTvContent(R.id.tc_description,getResources().getString(R.string.detail_description_label),true);
        setTvContent(R.id.tc_ingredients,getResources().getString(R.string.detail_ingredients_label),true);
        setTvContent(R.id.tc_known_as,getResources().getString(R.string.detail_also_known_as_label),true);
        setTvContent(R.id.tc_origin,getResources().getString(R.string.detail_place_of_origin_label),true);
    }
}
