package com.cfox.circleimageviewdemo.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : CircleImageViewDemo
 * <br/>PACKAGE_NAME : com.cfox.circleimageviewdemo.imageview
 * <br/>AUTHOR : CFOX
 * <br/>DATA : 2016/5/31 0031
 * <br/>TIME : 9:37
 * <br/>MSG :
 * <br/>************************************************
 */
public abstract class CircleImageViewBase extends ImageView {

    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;
    private int mBorderWidth = 10;                                  //圆形图片边界宽
    private int mBorderColor = Color.parseColor("#ffffff");         //圆形图片边界颜色
    private boolean useDefaultStyle = false;                        //使用默认图片显示
    private boolean isFill = false;                                 //填充
    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    protected abstract void initConfig();
    public CircleImageViewBase(Context paramContext) {
        super(paramContext);
        initConfig();
    }

    public CircleImageViewBase(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
        initConfig();
    }

    public CircleImageViewBase(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initConfig();
    }

    /**
     * 设置使用默认样式，即不进行图片圆形处理
     * <br/> 默认 false 不使用默认样式，如果不想进行图片园处理设置为 true 即可
     * @param useDefaultStyle boolean false : 图片圆形处理 ； true ：图片不进行圆形处理
     */
    public void setUseDefaultStyle(boolean useDefaultStyle) {
        this.useDefaultStyle = useDefaultStyle;
    }

    /**
     * 设置圆形图片边界宽
     * <br/> 默认 边界宽为 10 ，如果想无边界设置 0 即可
     * @param mBorderWidth
     */
    public void setBorderWidth(int mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
    }

    /**
     * 设置圆形图片边颜色
     * <br/> 默认 边界颜色为 白色（#FFFFFF）
     * @param mBorderColor
     */
    public void setBorderColor(int mBorderColor) {
        this.mBorderColor = getResources().getColor(mBorderColor);
    }

    /**
     * 设置填充，当图片小于设置的圆时，会自动拉伸图片使图片填充满整个圆
     * <br/> true 填充 ， false 不进行填充
     * @param isFill boolean : true 填充 ， false 不进行填充
     */
    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }

    @Override
    protected void onDraw(Canvas paramCanvas) {
        if (useDefaultStyle) {
            super.onDraw(paramCanvas);
            return;
        }
        final Drawable localDrawable = getDrawable();
        if (localDrawable == null)
            return;
        if (localDrawable instanceof NinePatchDrawable) {
            return;
        }
        if (this.paint == null) {
            final Paint localPaint = new Paint();
            localPaint.setFilterBitmap(false);
            localPaint.setAntiAlias(true);
            localPaint.setXfermode(MASK_XFERMODE);
            this.paint = localPaint;
        }

        int width = getWidth();
        int height = getHeight();

        int boundsWidth = width;
        int boundsHeight = height;

        int picWidth = getDrawable().getBounds().width();//获取图片实际的宽
        int picHeight = getDrawable().getBounds().height();//获取图片实际的高

        if(!isFill && picWidth > picHeight){
            boundsWidth = (int)((float) picWidth / (float)picHeight * (float)height);
            boundsHeight = height;
        }

        if(!isFill && picWidth < picHeight){
            boundsWidth = height;
            boundsHeight = (int)((float) picHeight / (float)picWidth * (float)width);
        }

        int whSize = Math.min(width,height);
        width = whSize;
        height = whSize;

        if(isFill || picWidth == picHeight){
            boundsWidth = width;
            boundsHeight = height;
        }

        /** 保存layer */
        int layer = paramCanvas.saveLayer(0.0F, 0.0F, width, height, null, Canvas.ALL_SAVE_FLAG);
        /** 设置drawable的大小 */
        localDrawable.setBounds(0, 0,boundsWidth, boundsHeight);
        /** 将drawable绑定到bitmap(this.mask)上面（drawable只能通过bitmap显示出来） */
        localDrawable.draw(paramCanvas);

        if (((this.mask == null) || (this.mask.isRecycled()))) {
            this.mask = createOvalBitmap(width, height);
        }

        /** 将bitmap画到canvas上面 */
        paramCanvas.drawBitmap(this.mask, 0.0F, 0.0F, this.paint);
        /** 将画布复制到layer上 */
        paramCanvas.restoreToCount(layer);
        drawBorder(paramCanvas, width, height);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }

    /**
     * 绘制最外面的边框
     *
     * @param canvas
     * @param width
     * @param height
     */
    private void drawBorder(Canvas canvas, final int width, final int height) {
        if (mBorderWidth == 0) {
            return;
        }
        final Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        /**
         * 坐标x：view宽度的一般 坐标y：view高度的一般 半径r：因为是view的宽度-border的一半
         */
        canvas.drawCircle(width >> 1, height >> 1, (width - mBorderWidth) >> 1, mBorderPaint);
        canvas = null;
    }

    /**
     * 获取一个bitmap，目的是用来承载drawable;
     * <p>
     * 将这个bitmap放在canvas上面承载，并在其上面画一个椭圆(其实也是一个圆，因为width=height)来固定显示区域
     *
     * @param width
     * @param height
     * @return
     */
    public Bitmap createOvalBitmap(final int width, final int height) {
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(width, height, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        /**
         * 设置椭圆的大小(因为椭圆的最外边会和border的最外边重合的，如果图片最外边的颜色很深，有看出有棱边的效果，所以为了让体验更加好，
         * 让其缩进padding px)
         */
        localCanvas.drawCircle(width >> 1,height >> 1,width - 2 >> 1, localPaint);
        return localBitmap;
    }
}
