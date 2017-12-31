package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint();

    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path1 = createCirclePath(point1);

        Path path2 = new Path();
        path2.addPath(createCirclePath(point2));
        path2.addRect(point2.x,point2.y,point2.x+bitmap.getWidth(), point2.y+bitmap.getWidth(), Path.Direction.CW);
        path2.setFillType(Path.FillType.EVEN_ODD);

        canvas.save();
        canvas.clipPath(path1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(path2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }

    /**
     * 生成一个圆弧路径
     * @param point
     * @return
     */
    private Path createCirclePath(Point point){
        Path path = new Path();
        path.addArc(point.x+50,point.y+50,point.x+60+bitmap.getWidth(),point.y+60+bitmap.getWidth(),140,180);
        path.lineTo(point.x+bitmap.getWidth(),point.y+bitmap.getHeight());
        path.close();
        return path;
    }
}
