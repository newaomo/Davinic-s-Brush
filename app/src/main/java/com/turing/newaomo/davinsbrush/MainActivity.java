package com.turing.newaomo.davinsbrush;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.turing.newaomo.davinsbrush.activity.gen.GenSimpleModeActivity;
import com.turing.newaomo.davinsbrush.adapter.MyFragmentPagerAdapter;
import com.turing.newaomo.davinsbrush.fragment.MyFragment1;
import com.turing.newaomo.davinsbrush.fragment.MyFragment2;
import com.turing.newaomo.davinsbrush.fragment.MyFragment3;
import com.turing.newaomo.davinsbrush.fragment.MyFragment4;
import com.turing.newaomo.davinsbrush.view.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private ActionBarDrawerToggle drawerToggle;
    ViewPager viewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter; //Fragment适配器
    private List<Fragment> myContionter;     //存放的容器
    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;
    private MyFragment3 myFragment3;
    private MyFragment4 myFragment4;

    private LinearLayout linearLayoutSimple;
    public static final int PAGE_1 = 0;
    public static final int PAGE_2 = 1;
    public static final int PAGE_3 = 2;
    public static final int PAGE_4 = 3;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initDrawer();
        linearLayoutSimple = (LinearLayout)findViewById(R.id.drawer_simple_layout);
        linearLayoutSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GenSimpleModeActivity.class);
                startActivity(intent);
            }
        });

        StatusBarUtil.setColor(MainActivity.this,Color.parseColor("#000000") );

        //得到当前界面的装饰视图
        if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //设置让应用主题内容占据状态栏和导航栏
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏和导航栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
//        //隐藏标题栏
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        initUI();

    }


    public void initDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation = (NavigationView) findViewById(R.id.drawer_navigation);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.openString,R.string.closeString){
            /*
            * 抽屉菜单打开监听
            * */
            @Override
            public void onDrawerOpened(View drawerView) {
//                Toast.makeText(MainActivity.this,"菜单打开了",Toast.LENGTH_SHORT).show();
                super.onDrawerOpened(drawerView);
            }
            /*
            * 抽屉菜单关闭监听
            * */
            @Override
            public void onDrawerClosed(View drawerView) {
//                Toast.makeText(MainActivity.this,"菜单关闭了",Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }
        };
        /*
        * NavigationView设置点击监听
        * */
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerToggle.syncState();
        //设置DrawerLayout的抽屉开关监听
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initUI() {
        viewPager = (ViewPager) findViewById(R.id.main_finally_viewpager);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();
        myFragment4 = new MyFragment4();
//初始化容器
        myContionter = new ArrayList<>();
        myContionter.add(myFragment1);
        myContionter.add(myFragment2);
        myContionter.add(myFragment3);
        myContionter.add(myFragment4);
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(4);          //设置活动的最大缓存数量

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.main_finally_ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home2),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.home2))
                        .title("主页")
//                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.main_nav_news),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.main_nav_news))
                        .title("新闻")
//                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("历史")
//                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.main_nav_setting),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.main_nav_setting))
                        .title("设置")
//                        .badgeTitle("icon")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                Log.d("-----","position is "+position);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
