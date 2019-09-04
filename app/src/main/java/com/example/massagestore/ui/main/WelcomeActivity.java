package com.example.massagestore.ui.main;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.example.massagestore.R;
import com.example.massagestore.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    private Animation animation;
    private ImageView imageView;

    @Override
    protected void init() {
        animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        imageView.setAnimation(animation);
        imageView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.img);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }
}
