package org.dalol.pixcollage.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/17/2017
 */
public class PixCollageMaskView extends ImageView {

    private Paint mPaint;

    public PixCollageMaskView(Context context) {
        this(context, null, 0);
    }

    public PixCollageMaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PixCollageMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PixCollageMaskView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        Bitmap bitmap = getClippedBitmap(width, height);


        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }

    private Bitmap getBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);
        return bitmap;
    }

    private Bitmap getClippedBitmap(int width, int height) {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, width, height, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(width / 2, height/2, width/3, paint);
        return output;
    }
}
