package com.lyzon.ui.clipbannerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by laoyongzhi on 2017/4/28.
 */

public class ClipBannerAdapter extends PagerAdapter{

    private Context mContext;

    //banner's size
    private int mSize;

    private OnBannerClickListener mClickListener;

    public interface OnBannerClickListener {

        void onBannerClick(int position);

    }

    protected ClipBannerAdapter(Context context,int size,OnBannerClickListener clickListener){
        this.mContext = context;
        this.mSize = size;
        this.mClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return mSize == 0 ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int realPosition = position % mSize;

        BannerItem bannerItem = new BannerItem(mContext);
        setBannerContent(bannerItem,realPosition);

        bannerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.onBannerClick(realPosition);
                }
            }
        });

        container.addView(bannerItem, 0);
        return bannerItem;
    }

    public void setBannerContent(BannerItem item,int position){
        //need to override
        //load your image into   [item.bannerImage] ;
        //set text into   [item.bannerText] ;
        //simple : Glide.load(bannerList.get(position).imageUrl).into(item.bannerImage);
        //simple : item.bannerText.setText(bannerList.get(position).bannerTitle);
        //if banner didn't have title to show , you should do it : item.bannerText.setVisibility(View.GONE);
    }


    public class BannerItem extends FrameLayout{

        public ImageView bannerImage;
        public TextView bannerText;

        public BannerItem(Context context) {
            this(context, null);
        }

        public BannerItem(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public BannerItem(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            View.inflate(context, R.layout.banner_item, this);
            bannerImage = (ImageView) this.findViewById(R.id.iv_banner);
            bannerText = (TextView) this.findViewById(R.id.tv_banner_title);
        }
    }

}
