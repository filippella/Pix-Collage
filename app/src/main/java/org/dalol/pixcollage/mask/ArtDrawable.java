package org.dalol.pixcollage.mask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/17/2017
 */
public class ArtDrawable extends Drawable {

    private Bitmap mBufferBitmap;
    private Canvas mBufferCanvas;
    private Bitmap mPictureBitmap;
    private Bitmap mMaskBitmap;

    private final Paint mPaintSrcIn = new Paint();

    public ArtDrawable() {
        mPaintSrcIn.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        final int width = bounds.width();
        final int height = bounds.height();

        if (width <= 0 || height <= 0) {
            return;
        }

        if (mBufferBitmap != null
                && mBufferBitmap.getWidth() == width
                && mBufferBitmap.getHeight() == height) {
            return;
        }

        mBufferBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //that's too bad
        mBufferCanvas = new Canvas(mBufferBitmap);
        redrawBufferCanvas();
    }

    private void redrawBufferCanvas() {
        if (mPictureBitmap == null || mMaskBitmap == null || mBufferCanvas == null) {
            return;
        }

        mBufferCanvas.drawBitmap(mMaskBitmap, 0, 0, null);
        mBufferCanvas.drawBitmap(mPictureBitmap, 0, 0, mPaintSrcIn);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mBufferBitmap, 0, 0, null);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaintSrcIn.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    public void setPictureBitmap(Bitmap pictureBitmap) {
        mPictureBitmap = pictureBitmap;
        redrawBufferCanvas();
    }

    public void setMaskBitmap(Bitmap maskBitmap) {
        mMaskBitmap = maskBitmap;
        redrawBufferCanvas();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public int getIntrinsicWidth() {
        return mMaskBitmap != null ? mMaskBitmap.getWidth() : super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mMaskBitmap != null ? mMaskBitmap.getHeight() : super.getIntrinsicHeight();
    }
}
