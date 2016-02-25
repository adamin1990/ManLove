package adamin.toolkits.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import adamin.toolkits.log.Logger;

/**
 * Created by LiTao on 2015-12-26-23:46.
 * Company: QD24so
 * Email: 14846869@qq.com
 * WebSite: http://lixiaopeng.top
 * 圆角imageview
 */
public class RoundImageView extends ImageView {
    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable=getDrawable();
        if(drawable==null){
            return;
        }

        if(getWidth()==0||getHeight()==0)
        {
            return;
        }
        Paint paint=new Paint();

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);


        PorterDuffXfermode xfermode=new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);//两者交集取上层

        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
        int saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);

        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
        paint.setXfermode(xfermode);
        float scaleWidth =((float) getWidth()) / bitmap.getWidth();
        float scaleHeight = ((float) getHeight()) / bitmap.getHeight();
        Logger.e(RoundImageView.class.getSimpleName(),scaleHeight+"ddd"+scaleWidth);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth*1.1f, scaleHeight*1.1f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        canvas.drawBitmap(bitmap, 0, 0, paint);
//        paint.setXfermode(null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));   //两者交集取下层，保留上层
        canvas.drawCircle(getWidth()/2,getHeight()/2,bitmap.getWidth()/2+5,paint);
        canvas.restore();
    }
}
