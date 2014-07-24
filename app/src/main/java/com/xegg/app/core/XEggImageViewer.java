package com.xegg.app.core;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.xegg.app.R;
import com.xegg.app.model.Post;

import java.util.Random;

public class XEggImageViewer {

    private ImageView imageView;

    XEggImageViewer(ImageView imageView) {
        this.imageView = imageView;
    }

    public void loadImageFromPost(Post post) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setDuration(5000);

        AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
        Random random = new Random(System.currentTimeMillis());

        Ion.with(imageView)
                .placeholder(R.drawable.egg_loading)
                .error(random.nextInt(2) == 1 ? R.drawable.egg_error : R.drawable.egg_error2)
                .animateLoad(rotateAnimation)
                .animateIn(fadeInAnimation)
                .load(post.getImage());
    }

}
