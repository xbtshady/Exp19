package xbt.exp19;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {

    public static final String OVERWATCH_NAME = "overWatch_name";

    public static final String OVERWATCH_IMAGE_ID = "overWatch_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //获取图片的name和ID
        OverWatch overWatch = (OverWatch) getIntent().getSerializableExtra(OVERWATCH_NAME);
        String overWatchName = overWatch.getName();
        int overWatchId = overWatch.getImageID();
       

        //标题栏设定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collaping_toobar);
        ImageView overWatchImageView = (ImageView) findViewById(R.id.overwatch_image_view);
        Glide.with(Main2Activity.this).load(overWatchId).into(overWatchImageView);
        setSupportActionBar(toolbar);

        //正文设定
        TextView overWatchTextView = (TextView) findViewById(R.id.overwatch_content_text);
        collapsingToolbar.setTitle(overWatchName);
        String overWatchContent = makeContentText(overWatchName);
        overWatchTextView.setText(overWatchContent);

        //让返回图标显示出来
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private String  makeContentText(String overWatchName){
        StringBuilder ContentText = new StringBuilder();
        for(int i = 0; i < 500; i++){
            ContentText.append(overWatchName);
        }
        return ContentText.toString();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
