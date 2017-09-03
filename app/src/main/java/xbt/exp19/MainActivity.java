package xbt.exp19;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
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
                Toast.makeText(MainActivity.this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(MainActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(MainActivity.this,"settings",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}