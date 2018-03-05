package org.beyondrefuge.www;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mc;
    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_player);
        mc = new MediaController(this);
        videoView = (VideoView) findViewById(R.id.video_view);
        close = (ImageView) findViewById(R.id.close_video);
        VideoPlay();
        Close();
    }

    private void VideoPlay() {
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // String videoUrl = preferences.getString("video_url", "rtsp://r4---sn-4g5e6nes.googlevideo.com/Cj0LENy73wIaNAnkoz1eRTBClBMYDSANFC0rnJpaMOCoAUIASARg99G_uZPa7YBaigELNV9EMjZiRC1HZTgM/6D523E438D61283FE88FBFC260D442AFFBCD308F.BD5B3501B7FC5F467346035FD7F0E38A051E267C/yt6/1/video.3gp");

        try {
            String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4 ";

            Uri uri = Uri.parse(videoUrl);

            videoView.setVideoURI(uri);

            videoView.setMediaController(mc);

            mc.setAnchorView(videoView);

            videoView.start();

        } catch (Exception e) {
            Toast.makeText(this, "There is a problem", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void Close() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
