package com.xegg.app.core;

import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.xegg.app.R;
import com.xegg.app.model.Post;

public class XEggImageViewer {

    private ImageView imageView;

    XEggImageViewer(ImageView imageView) {
        this.imageView = imageView;
    }

    public void loadImageFromPost(Post post) {
        Ion.with(imageView)
                .placeholder(R.drawable.ic_stub)
                .error(R.drawable.ic_error)
//                .animateLoad(R.drawable.ic_loading)
//                .animateIn(fadeInAnimation)
                .load(post.getImage());
    }

}
