package com.tisser.puneet.tisserartisan.UI.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tisser.puneet.tisserartisan.R;
import com.tisser.puneet.tisserartisan.UI.Activity.BaseActivity_NavDrawer;

public class AboutFragment extends BaseFragment
{
    private ImageView headerImg;
    private TextView abouttvcontact;
    private ImageView aboutimgphone;
    private TextView abouttvphone;
    private LinearLayout llphone;
    private ImageView aboutimgemail;
    private TextView abouttvemail;
    private LinearLayout llemail;
    private TextView abouttvinfo;
    private TextView abouttvinfotext;
    private TextView abouttvsocial;
    private ImageView aboutimgwebsite;
    private ImageView aboutimgfb;
    private ImageView aboutimgtwitter;
    private ImageView aboutimginstagram;
    private LinearLayout llsocial1;
    private ImageView aboutimglinkedin;
    private ImageView aboutimgpinterest;
    private ImageView aboutimgblogspot;
    private LinearLayout llsocial2;

    public static AboutFragment newInstance()
    {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    public AboutFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_about;
    }

    @Override
    protected String setupToolbarTitle()
    {
        return "About Tisser";
    }


    @Override
    protected void initializeLayout(View view)
    {
        this.llsocial2 = (LinearLayout) view.findViewById(R.id.ll_social_2);
        this.aboutimgblogspot = (ImageView) view.findViewById(R.id.about_img_blogspot);
        this.aboutimgpinterest = (ImageView) view.findViewById(R.id.about_img_pinterest);
        this.aboutimglinkedin = (ImageView) view.findViewById(R.id.about_img_linkedin);
        this.llsocial1 = (LinearLayout) view.findViewById(R.id.ll_social_1);
        this.aboutimginstagram = (ImageView) view.findViewById(R.id.about_img_instagram);
        this.aboutimgtwitter = (ImageView) view.findViewById(R.id.about_img_twitter);
        this.aboutimgfb = (ImageView) view.findViewById(R.id.about_img_fb);
        this.aboutimgwebsite = (ImageView) view.findViewById(R.id.about_img_website);
        this.abouttvsocial = (TextView) view.findViewById(R.id.about_tv_social);
        this.abouttvinfotext = (TextView) view.findViewById(R.id.about_tv_info_text);
        this.abouttvinfo = (TextView) view.findViewById(R.id.about_tv_info);
        this.llemail = (LinearLayout) view.findViewById(R.id.ll_email);
        this.abouttvemail = (TextView) view.findViewById(R.id.about_tv_email);
        this.aboutimgemail = (ImageView) view.findViewById(R.id.about_img_email);
        this.llphone = (LinearLayout) view.findViewById(R.id.ll_phone);
        this.abouttvphone = (TextView) view.findViewById(R.id.about_tv_phone);
        this.aboutimgphone = (ImageView) view.findViewById(R.id.about_img_phone);
        this.abouttvcontact = (TextView) view.findViewById(R.id.about_tv_contact);
        this.headerImg = (ImageView) view.findViewById(R.id.headerImg);
    }

    @Override
    protected void setupLayout()
    {
        abouttvphone.setText(manager.settings.getContact());
        abouttvemail.setText(manager.settings.getEmail());

        llphone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+91 " + manager.settings.getContact()));
                startActivity(callIntent);
            }
        });


        llemail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{manager.settings.getEmail()});
                i.putExtra(Intent.EXTRA_SUBJECT, "Tisser Android App Enquiry");
                String content = "";
                content = content.concat("\n\n\n - Sent via Tisser Android app");
                i.putExtra(Intent.EXTRA_TEXT, content);
                try
                {
                    startActivity(Intent.createChooser(i, "Send Email"));
                }
                catch (ActivityNotFoundException ex)
                {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        aboutimgwebsite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("http://www.tisserindia.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimgfb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.facebook.com/TisserIndia"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimgtwitter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://twitter.com/tisserindia"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimginstagram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://instagram.com/tisserindia/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimgpinterest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://in.pinterest.com/Tisserindia/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimglinkedin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.linkedin.com/in/tisser"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aboutimgblogspot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("http://tisserindia.blogspot.in/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


}
