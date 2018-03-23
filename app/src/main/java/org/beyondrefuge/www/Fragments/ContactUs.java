package org.beyondrefuge.www.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.beyondrefuge.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Recoded Cedan on 21.02.2018.
 */

public class ContactUs extends Fragment {
    @BindView(R.id.textView3)
    TextView beyondPhone;
    @BindView(R.id.beyond_mail)
    TextView beyondMail;
    @BindView(R.id.imageView5)
    ImageView beyondFacebook;
    @BindView(R.id.imageView4)
    ImageView beyondLinkedin;
    @BindView(R.id.imageView3)
    ImageView beyondTwitter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contactus, null);
        ButterKnife.bind(this, view);
        beyondPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "++447547285503";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        beyondMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","fred@beyondrefuge.org", null));
                startActivity(emailIntent);
            }
        });
        beyondFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/beyondrefuge";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        beyondLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/company/beyond-refuge/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        beyondTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/beyond_refuge";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return view;
    }
}

