package org.beyondrefuge.www;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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


    ArrayList<String> selectedTags = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_preference );
        ButterKnife.bind( this );
        if (selectedTags.isEmpty()) {
            doneButton.setVisibility( View.GONE );
        }

        cencelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Intent intentView= new Intent (PreferenceActivity.this,MainActivity.class);
               // startActivity( intentView );
            }
        } );


        TagContainerLayout mTagContainerLayout = (TagContainerLayout) findViewById( R.id.tag_view);
        int max = 1000000;
        final ArrayList<String> strings = new ArrayList<>(  );
        strings.add( "Racism" );
        strings.add( "Sexism" );
        strings.add( "Radicalism" );
        Collections.shuffle( strings );
        mTagContainerLayout.setTags( strings );
        mTagContainerLayout.setTagMaxLength(max);
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {

                doneButton.setVisibility(View.VISIBLE);
                if (selectedTags.contains( text ))
                    selectedTags.remove( position );
                else
                    selectedTags.add( text );

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
            doneButton.setEnabled( false );
        } else {
            doneButton.setEnabled( true );
        }
    }

    @OnClick(R.id.button_done)
    public void Done(View view){

        if (selectedTags.isEmpty()){

            Toast.makeText( this,"please choose at least one category" ,Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText( this,"You are welcome" ,Toast.LENGTH_LONG).show();

           // Intent intentView= new Intent (PreferenceActivity.this,MainActivity.class);
           // startActivity( intentView );
        }

    }

    @Override
    public void onClick(View v) {

    }
}
