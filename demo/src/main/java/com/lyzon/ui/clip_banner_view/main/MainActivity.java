package com.lyzon.ui.clip_banner_view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lyzon.ui.clip_banner_view.R;
import com.lyzon.ui.clipbannerview.ClipBannerAdapter;
import com.lyzon.ui.clipbannerview.ClipBannerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ClipBannerAdapter.OnBannerClickListener{

    private List<BannerEntity> entityList = new ArrayList<>();

    private ClipBannerView clipBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        clipBannerView = (ClipBannerView) findViewById(R.id.banner_view);
                                                        //context,banner's size,clickListener
        clipBannerView.setAdapter(new ClipBannerAdapter(this,entityList.size(),this) {
            @Override
            public void setBannerContent(BannerItem item,int position){
                //need to override
                //load your image into   * item.bannerImage * ;
                //set text into   * item.bannerText * ;
                //simple : Glide.load(bannerList.get(position).imageUrl).into(item.bannerImage);
                //simple : item.bannerText.setText(bannerList.get(position).bannerTitle);
                //if banner didn't have title to show , you should do it : item.bannerText.setVisibility(View.GONE);
                Glide.with(MainActivity.this).load(entityList.get(position).imageUrl).into(item.bannerImage);
                item.bannerText.setText(entityList.get(position).bannerTitle);
                if(item.bannerText.getText().length() == 0)
                    item.bannerText.setVisibility(View.GONE);
            }
        });
        clipBannerView.start();

    }

    @Override
    public void onBannerClick(int position){

        Toast.makeText(this, entityList.get(position).bannerTitle, Toast.LENGTH_SHORT).show();
    }

    private void initData(){
        BannerEntity entity = new BannerEntity();
        entity.imageUrl = "https://cdn.sspai.com/article/e143461c-7174-c0e0-7489-2ba6505c2052.jpg?imageMogr2/quality/95/thumbnail/!x372r/gravity/Center/crop/x372";
        entity.bannerTitle = "产品经理都在用哪些工具?";
        entityList.add(entity);

        BannerEntity entity1 = new BannerEntity();
        entity1.imageUrl = "http://i0.hdslb.com/bfs/archive/91d62da88b52084e6847111ba285a1aaa5d219fe.png@.webp";
        entity1.bannerTitle = "五一 脑洞节！ 一起来大开脑洞吧~";
        entityList.add(entity1);

        BannerEntity entity2 = new BannerEntity();
        entity2.imageUrl = "https://cdn.sspai.com/other/136_1490237571570.jpg?imageMogr2/quality/95/thumbnail/!640x320r/gravity/Center/crop/640x320";
        entity2.bannerTitle = "玩转 workflow ， 对于大多数人来说，Workflow 依然难以上手，各种技巧分享显得很有必要 ";
        entityList.add(entity2);

        BannerEntity entity3 = new BannerEntity();
        entity3.imageUrl = "https://i0.hdslb.com/bfs/archive/1b91a00920ecb1242a27adbf64183011b328e599.jpg@.webp";
        entity3.bannerTitle = "";
        entityList.add(entity3);
    }

}
