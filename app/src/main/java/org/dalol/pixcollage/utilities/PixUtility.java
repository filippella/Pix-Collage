package org.dalol.pixcollage.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/17/2017
 */
public final class PixUtility {

    public static void createImage(View view) {
        Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view.draw(c);
    }
}
