package org.beyondrefuge.www;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.button_done) Button doneButton;
    @BindView( R.id.button_image_cencel ) ImageButton cencelButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference();

    ArrayList<String> selectedTags = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_preference );
        ButterKnife.bind( this );

        cencelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );


        final TagContainerLayout mTagContainerLayout = (TagContainerLayout) findViewById( R.id.tag_view);
        int max = 1000000;
        final int tagColor = getResources().getColor(R.color.colorPrimaryDark);
        final ArrayList<String> strings = new ArrayList<>(  );
        strings.add( "Racism" );
        strings.add( "Sexism" );
        strings.add( "Radicalism" );
        mAuth = FirebaseAuth.getInstance();
        Collections.shuffle( strings );
        mTagContainerLayout.setTags( strings );
        mTagContainerLayout.setTagMaxLength(max);
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {



                if (selectedTags.contains( text )) {
                    ((TagView)(mTagContainerLayout.getChildAt( position ))).setBackgroundColor( tagColor );
                    selectedTags.remove( text );
                }

                else {
                    ((TagView)(mTagContainerLayout.getChildAt( position ))).setBackgroundColor( Color.BLUE );
                    selectedTags.add( text );
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("taggedWord", text);
                    editor.commit();
                }


                checkDoneButton ();
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

    }

    private void checkDoneButton() {
        if (selectedTags.isEmpty()) {
            doneButton.setVisibility( View.GONE );
        } else {
            doneButton.setVisibility(View.VISIBLE );
        }
    }

    @OnClick(R.id.button_done)
    public void Done(View view){

        if (selectedTags.isEmpty()){

            Toast.makeText( this,"please choose at least one category" ,Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText( this,"You are welcome" ,Toast.LENGTH_LONG).show();

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
            reference.child("users").child(mAuth.getCurrentUser().getUid()).child("tagCompleted").setValue("true");

            Intent intentView= new Intent(PreferenceActivity.this,MainActivity.class);
            startActivity( intentView );
        }

    }

    @Override
    public void onClick(View v) {

    }

}
