package org.lu.hypervisor.android.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textview.MaterialTextView;

import org.lu.hypervisor.android.R;
import org.lu.hypervisor.android.api.model.Misbehavior;
import org.lu.hypervisor.android.api.model.Photo;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import de.hdodenhof.circleimageview.CircleImageView;

public class MisbehaviorView extends LinearLayout {
    private CircleImageView avatar;
    private MaterialTextView title;
    private MaterialTextView desc;
    public MisbehaviorView(Context context) {
        this(context,(AttributeSet) null);
    }

    public MisbehaviorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MisbehaviorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MisbehaviorView(Context context,Misbehavior misbehavior,Photo photo){
        this(context,(AttributeSet) null);
        this.bindModel(misbehavior,photo);
    }
    private void init(){
        inflate(getContext(), R.layout.misbehavior_item,this);
        avatar = findViewById(R.id.misbehavior_card_avatar);
        title = findViewById(R.id.misbehavior_card_title);
        desc = findViewById(R.id.misbehavior_card_desc);
    }
    public void bindModel(Misbehavior misbehavior, Photo photo){
        this.title.setText(misbehavior.getType().toString());
        this.desc.setText(misbehavior.getWhen().toString());
        this.avatar.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.getDecoder().decode(photo.getPhotoBase64()))));
    }
}
