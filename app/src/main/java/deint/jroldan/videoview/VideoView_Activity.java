package deint.jroldan.videoview;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoView_Activity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.billgates);     // Video
        //Uri uri = Uri.parse("android:resource://"+getPackageName()+"/"+R.raw.billgates);
        //videoView.setVideoPath((Environment.getExternalStorageDirectory().getAbsolutePath()+"nombrefichero"));
        mc=new MediaController(this);
        videoView.setMediaController(mc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.suspend();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    // NO GARANTIZA QUE SE EJECUTE ANTES QUE onPause()
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int position = videoView.getCurrentPosition();
        outState.putInt("position", position);
        // Comprobar en la segunda llamada a onSaveInstanceState no modificar
        // el valor de playing
        outState.putBoolean("playing", videoView.isPlaying());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        videoView.seekTo(savedInstanceState.getInt("position"));
        if(!savedInstanceState.getBoolean("playing"))
            videoView.pause();
    }
}
