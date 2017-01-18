package org.dalol.pixcollage.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/17/2017
 */
public class PixCollageView extends RelativeLayout {

    public PixCollageView(Context context) {
        this(context, null, 0);
    }

    public PixCollageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PixCollageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PixCollageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr);
    }

    public void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        verifyInitMode();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        verifyInitMode();
    }


    private void verifyInitMode() {
        if(isInEditMode()) {
            return;
        }
    }
}
