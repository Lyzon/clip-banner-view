### Clip Banner View
a simple and easy to used banner view for andorid

it looks like this :

![demo.gif](http://ac-whikwudz.clouddn.com/ee145ada67086845b866.gif)

#### How to use?

#### Gradle
Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.lyzon:clip-banner-view:1.0.0'
	}
	
Step 3. In your layout xml:

    <Banner view 's root layout
        ...
        android:clipChildren="false"
        ...
        >
            <com.lyzon.ui.clipbannerview.ClipBannerView
                android:layout_marginTop="8dp"
                android:id="@+id/banner_view"
                android:layout_width="match_parent"
                android:layout_height="168dp"
                android:layout_marginLeft="16dp"
                android:layout_marginRight="16dp"
                android:clipChildren="false" >
            </com.lyzon.ui.clipbannerview.ClipBannerView>
            
    </Banner view 's root layout>
    
don't forget add

    android:clipChildren="false"
    
in the banner view and root viewgroup...

Step 4. In java code:

        clipBannerView = (ClipBannerView) findViewById(R.id.banner_view);
                                                     
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
        


you just need to set the

    ClipBannerAdapter(Context context,int bannerSize,OnBannerClickListener listener)
    
to the bannerview and override the 
    
    @Override
    setBannerContent(BannerItem item,int position){
                //must be override
                //load your image into   * item.bannerImage * ;
                //set text into   * item.bannerText * ;
                //simple : Glide.load(bannerList.get(position).imageUrl).into(item.bannerImage);
                //simple : item.bannerText.setText(bannerList.get(position).bannerTitle);
                //if banner didn't have title to show , you should do it : item.bannerText.setVisibility(View.GONE);
    }
    
then 

    clipBannerView.start();
    
last in your listener

    @Override
    public void onBannerClick(int position){
    	// do what you want...
    }
    
#### More

you can use the
    
    clipBannerView.setShowTime(int showTimeMillis){} 
    
to change show time

    clipBannerView.stop();
    
to stop play

    clipBannerView.previous();
    
go previous 

    clipBannerView.next();
    
to next 



this banner view is a ViewPager,so you can use it like a ViewPager.

    clipBannerView.setCurrentItem(int);
    ...
    ...
    ...
    ...
    ...
    
Bad English....
