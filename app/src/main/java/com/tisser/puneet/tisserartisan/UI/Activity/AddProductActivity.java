package com.tisser.puneet.tisserartisan.UI.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.tisser.puneet.tisserartisan.UI.Adapters.GalleryImagesAdapter;
import com.tisser.puneet.tisserartisan.Custom.MarginDecoration;
import com.tisser.puneet.tisserartisan.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddProductActivity extends BaseActivity
{
    @Bind(R.id.upload_button) Button mUploadButton;
    @Bind(R.id.gallery_images_recycler) RecyclerView mGalleryImagesRecycler;
    @Bind(R.id.ll_select_category) View selectCategoryLL;
    @Bind(R.id.ll_select_color) View selectColorLL;

    private ArrayList<Image> images;
    private GridLayoutManager mLayoutManager;
    private GalleryImagesAdapter mAdapter;

    ImageView categoryIcon, colorIcon;
    TextView categoryText, colorText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_add_product;
    }

    @Override
    protected void setupToolbar()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setTitle("Add New Product");
    }

    @Override
    protected void setupLayout()
    {
        categoryText = ButterKnife.findById(selectCategoryLL, R.id.selector_text);
        colorText = ButterKnife.findById(selectColorLL, R.id.selector_text);
        categoryText.setText("Select Category");
        colorText.setText("Select Color(s)");

        categoryIcon = ButterKnife.findById(selectCategoryLL, R.id.selector_icon);
        colorIcon = ButterKnife.findById(selectColorLL, R.id.selector_icon);
        categoryIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_help_grey_24dp));
        colorIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_palette_grey_24dp));


        mUploadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddProductActivity.this, AlbumSelectActivity.class);
                intent.putExtra("" + Constants.INTENT_EXTRA_LIMIT, com.tisser.puneet.tisserartisan.Global.Constants.GALLERY_NUM_IMGS_TO_SELECT);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });

        mGalleryImagesRecycler.addItemDecoration(new MarginDecoration(this));

        mLayoutManager = new GridLayoutManager(this, 2);

        mGalleryImagesRecycler.setLayoutManager(mLayoutManager);

        mAdapter = new GalleryImagesAdapter(this, null);
        mGalleryImagesRecycler.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);

            mAdapter.addAll(images);
            mGalleryImagesRecycler.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
                    {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onGlobalLayout()
                        {
                            mGalleryImagesRecycler.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int viewWidth = mGalleryImagesRecycler.getMeasuredWidth();
                            float cardViewWidth = findViewById(R.id.img_product_thumb).getMeasuredWidth();
                            int newSpanCount = (int) Math.floor(viewWidth / cardViewWidth);
                            mLayoutManager.setSpanCount(newSpanCount);
                            mLayoutManager.requestLayout();
                        }
                    });
            mGalleryImagesRecycler.setAdapter(mAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}