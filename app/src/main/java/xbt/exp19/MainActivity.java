package xbt.exp19;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private OverWatch[] overWatches = {new OverWatch("猎空",R.drawable.img_1),new OverWatch("猎空",R.drawable.img_2),
            new OverWatch("猎空",R.drawable.img_3),new OverWatch("猎空",R.drawable.img_5),
            new OverWatch("猎空",R.drawable.img_6),new OverWatch("猎空",R.drawable.img_7),
            new OverWatch("猎空",R.drawable.img_8),new OverWatch("猎空",R.drawable.img_9),
            new OverWatch("猎空",R.drawable.img_10),new OverWatch("猎空",R.drawable.img_11)};

    private List<OverWatch> overWatchList = new ArrayList<>();

    private OverWatchAdapter overWatchAdapter;

    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //标题栏Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //滑动菜单的导航键
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //让ActionBar的HomeAsUp按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //改变HomeAsUp按钮的图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_name);
        }

        //滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        //悬浮按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"这是一个提醒", Snackbar.LENGTH_SHORT)
                        .setAction("可点", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"你点了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        //图片加载
        initOverWatch();
        RecyclerView reccyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        reccyclerView.setLayoutManager(layoutManager);
        overWatchAdapter = new OverWatchAdapter(overWatchList);
        reccyclerView.setAdapter(overWatchAdapter);

        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshOverWatch();
            }
        });
    }

    /*
    加载菜单项
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /*
    标题栏按钮的响应事件
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//打开标题栏
                break;
            case R.id.backup:
                Intent intent = new Intent("XX");
                intent.addCategory("ww");
                intent.addCategory("xw");
                startActivity(intent);
                Toast.makeText(MainActivity.this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
                Toast.makeText(MainActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:10086"));
                startActivity(intent2);
                Toast.makeText(MainActivity.this,"settings",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    //存放图片
    private  void initOverWatch(){
        overWatchList.clear();
        for (int i = 0; i < 50; i++){
            Random random = new Random();
            int index = random.nextInt(overWatches.length);
            overWatchList.add(overWatches[index]);
        }
    }

    //刷新图片
    private  void refreshOverWatch(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initOverWatch();
                        overWatchAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
