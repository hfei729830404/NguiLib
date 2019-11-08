package com.ng.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.ng.nguilib.utils.DensityUtil;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 1, 15},
   bv = {1, 0, 3},
   k = 1,
   d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010<\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010=\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J\b\u0010>\u001a\u000208H\u0002J\b\u0010?\u001a\u000208H\u0002J\b\u0010@\u001a\u000208H\u0002J\b\u0010A\u001a\u000208H\u0014J\u0010\u0010B\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0014J0\u0010C\u001a\u0002082\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020\u000eH\u0014J\u0018\u0010J\u001a\u0002082\b\u0010K\u001a\u0004\u0018\u00010\u001e2\u0006\u0010L\u001a\u00020\u000eJ\u000e\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020\bJ\b\u0010O\u001a\u000208H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001fR\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000¨\u0006P"},
   d2 = {"Lcom/ng/ui/view/EcgShowView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "GRID_LINE_STROKE_WIDTH", "", "GRID_WIDTH_AND_HEIGHT", "HEART_LINE_STROKE_WIDTH", "INTERVAL_SCROLL_REFRESH", "MAX_VALUE", "SHOW_MODEL", "", "getSHOW_MODEL", "()I", "setSHOW_MODEL", "(I)V", "SHOW_MODEL_ALL", "getSHOW_MODEL_ALL", "SHOW_MODEL_DYNAMIC_REFRESH", "getSHOW_MODEL_DYNAMIC_REFRESH", "SHOW_MODEL_DYNAMIC_SCROLL", "getSHOW_MODEL_DYNAMIC_SCROLL", "column", "data", "", "dataStrList", "", "", "[Ljava/lang/String;", "intervalColumn", "intervalColumnHeart", "intervalNumHeart", "intervalRow", "intervalRowHeart", "mGridLinestrokeWidth", "mGridstrokeWidthAndHeight", "mHeartLinestrokeWidth", "mHeight", "mWidth", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "refreshList", "", "row", "scrollIndex", "showIndex", "timer", "Ljava/util/Timer;", "timerTask", "Ljava/util/TimerTask;", "drawGrid", "", "canvas", "Landroid/graphics/Canvas;", "drawHeartAll", "drawHeartRefresh", "drawHeartScroll", "init", "initData", "logdata", "onDetachedFromWindow", "onDraw", "onLayout", "changed", "", "left", "top", "right", "bottom", "setModel", "dataStr", "model", "showLine", "point", "startScrollTimer", "nguilib_debug"}
)
public final class EcgShoeView extends View {
   public int SHOW_MODEL;
   public final int SHOW_MODEL_ALL=1;
   public final int SHOW_MODEL_DYNAMIC_SCROLL;
   public final int SHOW_MODEL_DYNAMIC_REFRESH;
   private float mWidth;
   private float mHeight;
   private Paint paint;
   private Path path;
   private String[] dataStrList;
   private int scrollIndex;
   private Timer timer;
   private TimerTask timerTask;
   private final float INTERVAL_SCROLL_REFRESH;
   private List refreshList;
   private int showIndex;
   private final float MAX_VALUE;
   private int intervalNumHeart;
   private float intervalRowHeart;
   private float intervalColumnHeart;
   private final float HEART_LINE_STROKE_WIDTH;
   private float[] data;
   private float mHeartLinestrokeWidth;
   private final float GRID_LINE_STROKE_WIDTH;
   private final float GRID_WIDTH_AND_HEIGHT;
   private int row;
   private float intervalRow;
   private int column;
   private float intervalColumn;
   private float mGridLinestrokeWidth;
   private float mGridstrokeWidthAndHeight;
   private HashMap _$_findViewCache;

   public final int getSHOW_MODEL() {
      return this.SHOW_MODEL;
   }

   public final void setSHOW_MODEL(int var1) {
      this.SHOW_MODEL = var1;
   }

   public final int getSHOW_MODEL_ALL() {
      return this.SHOW_MODEL_ALL;
   }

   public final int getSHOW_MODEL_DYNAMIC_SCROLL() {
      return this.SHOW_MODEL_DYNAMIC_SCROLL;
   }

   public final int getSHOW_MODEL_DYNAMIC_REFRESH() {
      return this.SHOW_MODEL_DYNAMIC_REFRESH;
   }

   private final void init() {
      this.paint = new Paint();
      this.path = new Path();
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      this.mWidth = (float)this.getMeasuredWidth();
      this.mHeight = (float)this.getMeasuredHeight();
      DensityUtil var10001 = DensityUtil.INSTANCE;
      Context var10002 = this.getContext();
      Intrinsics.checkExpressionValueIsNotNull(var10002, "context");
      this.mGridLinestrokeWidth = (float)var10001.dip2px(var10002, this.GRID_LINE_STROKE_WIDTH);
      var10001 = DensityUtil.INSTANCE;
      var10002 = this.getContext();
      Intrinsics.checkExpressionValueIsNotNull(var10002, "context");
      this.mGridstrokeWidthAndHeight = (float)var10001.dip2px(var10002, this.GRID_WIDTH_AND_HEIGHT);
      this.column = (int)(this.mWidth / this.mGridstrokeWidthAndHeight);
      this.intervalColumn = this.mWidth / (float)this.column;
      this.row = (int)(this.mHeight / this.mGridstrokeWidthAndHeight);
      this.intervalRow = this.mHeight / (float)this.row;
      var10001 = DensityUtil.INSTANCE;
      var10002 = this.getContext();
      Intrinsics.checkExpressionValueIsNotNull(var10002, "context");
      this.mHeartLinestrokeWidth = (float)var10001.dip2px(var10002, this.HEART_LINE_STROKE_WIDTH);
      this.initData();
   }

   protected void onDraw(@NotNull Canvas canvas) {
      Intrinsics.checkParameterIsNotNull(canvas, "canvas");
      super.onDraw(canvas);
      this.drawGrid(canvas);
      int var2 = this.SHOW_MODEL;
      if (var2 == this.SHOW_MODEL_ALL) {
         this.drawHeartAll(canvas);
      } else if (var2 == this.SHOW_MODEL_DYNAMIC_SCROLL) {
         this.drawHeartScroll(canvas);
      } else if (var2 == this.SHOW_MODEL_DYNAMIC_REFRESH) {
         this.drawHeartRefresh(canvas);
      }

   }

   public final void showLine(float point) {
      if (this.refreshList == null) {
         this.refreshList = (List)(new ArrayList());
         this.data = new float[this.intervalNumHeart];
      }

      List var10000 = this.refreshList;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.add(point);
      this.postInvalidate();
   }

   private final void drawHeartRefresh(Canvas canvas) {
      Paint var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.reset();
      Path var10 = this.path;
      if (var10 == null) {
         Intrinsics.throwNpe();
      }

      var10.reset();
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setStyle(Style.STROKE);
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setColor(Color.parseColor("#31CE32"));
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setStrokeWidth(this.mGridLinestrokeWidth);
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setAntiAlias(true);
      var10 = this.path;
      if (var10 == null) {
         Intrinsics.throwNpe();
      }

      var10.moveTo(0.0F, this.mHeight / (float)2);
      int var12;
      if (this.refreshList == null) {
         var12 = 0;
      } else {
         List var13 = this.refreshList;
         if (var13 == null) {
            Intrinsics.throwNpe();
         }

         var12 = var13.size();
      }

      int nowIndex = var12;
      if (nowIndex != 0) {
         this.showIndex = nowIndex < this.intervalNumHeart ? nowIndex - 1 : (nowIndex - 1) % this.intervalNumHeart;
         int i = 0;
         int temp;
         float[] var15;
         for(int var4 = this.intervalNumHeart; i < var4; ++i) {
            List var10001 = this.refreshList;
            if (var10001 == null) {
               Intrinsics.throwNpe();
            }

            if (i > var10001.size() - 1) {
               break;
            }

            List var10002;
            if (nowIndex <= this.intervalNumHeart) {
               var15 = this.data;
               if (var15 == null) {
                  Intrinsics.throwNpe();
               }

               var10002 = this.refreshList;
               if (var10002 == null) {
                  Intrinsics.throwNpe();
               }

               var15[i] = ((Number)var10002.get(i)).floatValue();
            } else {
               i = (nowIndex - 1) / this.intervalNumHeart;
               temp = i * this.intervalNumHeart + i;
               if (temp < nowIndex) {
                  var15 = this.data;
                  if (var15 == null) {
                     Intrinsics.throwNpe();
                  }

                  var10002 = this.refreshList;
                  if (var10002 == null) {
                     Intrinsics.throwNpe();
                  }

                  var15[i] = ((Number)var10002.get(temp)).floatValue();
               }
            }
         }

         this.logdata();
         float nowX = 0.0F;
         float nowY = 0.0F;
         i = 0;
         var15 = this.data;
         if (var15 == null) {
            Intrinsics.throwNpe();
         }

         for(temp = var15.length; i < temp; ++i) {
            nowX = (float)i * this.intervalRowHeart;
            var15 = this.data;
            if (var15 == null) {
               Intrinsics.throwNpe();
            }

            float dataValue = var15[i];
            if (dataValue > (float)0) {
               if (dataValue > this.MAX_VALUE * 0.8F) {
                  dataValue = this.MAX_VALUE * 0.8F;
               }
            } else if (dataValue < -this.MAX_VALUE * 0.8F) {
               dataValue = -(this.MAX_VALUE * 0.8F);
            }

            nowY = this.mHeight / (float)2 - dataValue * this.intervalColumnHeart;
            if (i - 1 == this.showIndex) {
               var10 = this.path;
               if (var10 == null) {
                  Intrinsics.throwNpe();
               }

               var10.moveTo(nowX, nowY);
            } else {
               var10 = this.path;
               if (var10 == null) {
                  Intrinsics.throwNpe();
               }

               var10.lineTo(nowX, nowY);
            }
         }

         Path var11 = this.path;
         if (var11 == null) {
            Intrinsics.throwNpe();
         }

         Paint var14 = this.paint;
         if (var14 == null) {
            Intrinsics.throwNpe();
         }

         canvas.drawPath(var11, var14);
      }
   }

   private final void logdata() {
      String str = "";
      float[] var10000 = this.data;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      float[] var4 = var10000;
      int var5 = var4.length;

      for(int var3 = 0; var3 < var5; ++var3) {
         float temp = var4[var3];
         str = str + temp + ',';
      }

   }

   private final void drawHeartScroll(Canvas canvas) {
      if (this.data != null) {
         float[] var10000 = this.data;
         if (var10000 == null) {
            Intrinsics.throwNpe();
         }

         float[] var2 = var10000;
         boolean var3 = false;
         if (var2.length != 0) {
            Paint var11 = this.paint;
            if (var11 == null) {
               Intrinsics.throwNpe();
            }

            var11.reset();
            Path var12 = this.path;
            if (var12 == null) {
               Intrinsics.throwNpe();
            }

            var12.reset();
            var11 = this.paint;
            if (var11 == null) {
               Intrinsics.throwNpe();
            }

            var11.setStyle(Style.STROKE);
            var11 = this.paint;
            if (var11 == null) {
               Intrinsics.throwNpe();
            }

            var11.setColor(Color.parseColor("#31CE32"));
            var11 = this.paint;
            if (var11 == null) {
               Intrinsics.throwNpe();
            }

            var11.setStrokeWidth(this.mGridLinestrokeWidth);
            var11 = this.paint;
            if (var11 == null) {
               Intrinsics.throwNpe();
            }

            var11.setAntiAlias(true);
            var12 = this.path;
            if (var12 == null) {
               Intrinsics.throwNpe();
            }

            var12.moveTo(0.0F, this.mHeight / (float)2);
            int scrollEndIndex = this.scrollIndex;
            int scrollStartIndex = scrollEndIndex - this.intervalNumHeart;
            if (scrollStartIndex < 0) {
               scrollStartIndex = 0;
            }

            float nowX = 0.0F;
            float nowY = 0.0F;
            int i = scrollStartIndex;

            for(int var7 = scrollEndIndex; i < var7; ++i) {
               nowX = (float)(i - scrollStartIndex) * this.intervalRowHeart;
               var10000 = this.data;
               if (var10000 == null) {
                  Intrinsics.throwNpe();
               }

               float dataValue = var10000[i];
               if (dataValue > (float)0) {
                  if (dataValue > this.MAX_VALUE * 0.8F) {
                     dataValue = this.MAX_VALUE * 0.8F;
                  }
               } else if (dataValue < -this.MAX_VALUE * 0.8F) {
                  dataValue = -(this.MAX_VALUE * 0.8F);
               }

               nowY = this.mHeight / (float)2 - dataValue * this.intervalColumnHeart;
               var12 = this.path;
               if (var12 == null) {
                  Intrinsics.throwNpe();
               }

               var12.lineTo(nowX, nowY);
            }

            Path var10001 = this.path;
            if (var10001 == null) {
               Intrinsics.throwNpe();
            }

            Paint var10002 = this.paint;
            if (var10002 == null) {
               Intrinsics.throwNpe();
            }

            canvas.drawPath(var10001, var10002);
            this.postInvalidate();
            return;
         }
      }

   }

   private final void drawHeartAll(Canvas canvas) {
      if (this.data != null) {
         float[] var10000 = this.data;
         if (var10000 == null) {
            Intrinsics.throwNpe();
         }

         float[] var2 = var10000;
         boolean var3 = false;
         if (var2.length != 0) {
            Paint var9 = this.paint;
            if (var9 == null) {
               Intrinsics.throwNpe();
            }

            var9.reset();
            Path var10 = this.path;
            if (var10 == null) {
               Intrinsics.throwNpe();
            }

            var10.reset();
            var9 = this.paint;
            if (var9 == null) {
               Intrinsics.throwNpe();
            }

            var9.setStyle(Style.STROKE);
            var9 = this.paint;
            if (var9 == null) {
               Intrinsics.throwNpe();
            }

            var9.setColor(Color.parseColor("#31CE32"));
            var9 = this.paint;
            if (var9 == null) {
               Intrinsics.throwNpe();
            }

            var9.setStrokeWidth(this.mGridLinestrokeWidth);
            var9 = this.paint;
            if (var9 == null) {
               Intrinsics.throwNpe();
            }

            var9.setAntiAlias(true);
            var10 = this.path;
            if (var10 == null) {
               Intrinsics.throwNpe();
            }

            var10.moveTo(0.0F, this.mHeight / (float)2);
            float nowX = 0.0F;
            float nowY = 0.0F;
            int i = 0;
            var10000 = this.data;
            if (var10000 == null) {
               Intrinsics.throwNpe();
            }

            for(int var5 = var10000.length; i < var5; ++i) {
               nowX = (float)i * this.intervalRowHeart;
               var10000 = this.data;
               if (var10000 == null) {
                  Intrinsics.throwNpe();
               }

               float dataValue = var10000[i];
               if (dataValue > (float)0) {
                  if (dataValue > this.MAX_VALUE * 0.8F) {
                     dataValue = this.MAX_VALUE * 0.8F;
                  }
               } else if (dataValue < -this.MAX_VALUE * 0.8F) {
                  dataValue = -(this.MAX_VALUE * 0.8F);
               }

               nowY = this.mHeight / (float)2 - dataValue * this.intervalColumnHeart;
               var10 = this.path;
               if (var10 == null) {
                  Intrinsics.throwNpe();
               }

               var10.lineTo(nowX, nowY);
            }

            Path var10001 = this.path;
            if (var10001 == null) {
               Intrinsics.throwNpe();
            }

            Paint var10002 = this.paint;
            if (var10002 == null) {
               Intrinsics.throwNpe();
            }

            canvas.drawPath(var10001, var10002);
            return;
         }
      }

   }

   private final void drawGrid(Canvas canvas) {
      Paint var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setStyle(Style.STROKE);
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setColor(Color.parseColor("#D8D8D8"));
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setStrokeWidth(this.mGridLinestrokeWidth);
      var10000 = this.paint;
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      var10000.setAntiAlias(true);
      int i = 0;
      int var3 = this.column;
      Path var5;
      if (i <= var3) {
         while(true) {
            float iTempC = (float)i * this.intervalColumn;
            var5 = this.path;
            if (var5 == null) {
               Intrinsics.throwNpe();
            }

            var5.moveTo(iTempC, 0.0F);
            var5 = this.path;
            if (var5 == null) {
               Intrinsics.throwNpe();
            }

            var5.lineTo(iTempC, this.mHeight);
            if (i == var3) {
               break;
            }

            ++i;
         }
      }

      i = 0;
      var3 = this.row;
      if (i <= var3) {
         while(true) {
            var5 = this.path;
            if (var5 == null) {
               Intrinsics.throwNpe();
            }

            var5.moveTo(0.0F, (float)i * this.intervalRow);
            var5 = this.path;
            if (var5 == null) {
               Intrinsics.throwNpe();
            }

            var5.lineTo(this.mWidth, (float)i * this.intervalRow);
            if (i == var3) {
               break;
            }

            ++i;
         }
      }

      Path var10001 = this.path;
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }

      Paint var10002 = this.paint;
      if (var10002 == null) {
         Intrinsics.throwNpe();
      }

      canvas.drawPath(var10001, var10002);
   }

   public final void setData(@Nullable String dataStr, int model) {
      if (dataStr != null) {
         boolean $i$f$toTypedArray;
         List var10000;
         List var17;
         label35: {
            CharSequence var3 = (CharSequence)dataStr;
            String var4 = ",";
            boolean var5 = false;
            Regex var11 = new Regex(var4);
            byte var14 = 0;
            boolean var6 = false;
            var17 = var11.split(var3, var14);
            $i$f$toTypedArray = false;
            if (!var17.isEmpty()) {
               ListIterator iterator$iv = var17.listIterator(var17.size());

               while(iterator$iv.hasPrevious()) {
                  String it = (String)iterator$iv.previous();
                  int var7 ;
                  CharSequence var8 = (CharSequence)it;
                  boolean var9 = false;
                  if (var8.length() != 0) {
                     var10000 = CollectionsKt.take((Iterable)var17, iterator$iv.nextIndex() + 1);
                     break label35;
                  }
               }
            }

            var10000 = CollectionsKt.emptyList();
         }

         var17 = var10000;
         Collection $this$toTypedArray$iv = (Collection)var17;
         $i$f$toTypedArray = false;
         if ($this$toTypedArray$iv == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
         }

         Object[] var19 = $this$toTypedArray$iv.toArray(new String[0]);
         if (var19 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
         }

         Object[] var18 = var19;
         this.dataStrList = (String[])var18;
      }

      this.SHOW_MODEL = model;
      this.initData();
   }

   private final void initData() {
      int var2 = this.SHOW_MODEL;
      int i;
      int var4;
      int dataLength;
      float[] var6;
      String[] var10000;
      String[] var10002;
      if (var2 == this.SHOW_MODEL_ALL) {
         var10000 = this.dataStrList;
         if (var10000 == null) {
            Intrinsics.throwNpe();
         }

         dataLength = var10000.length;
         if ((float)dataLength > this.mWidth) {
            dataLength = (int)this.mWidth;
         }

         this.data = new float[dataLength];
         i = 0;

         for(var4 = dataLength; i < var4; ++i) {
            var6 = this.data;
            if (var6 == null) {
               Intrinsics.throwNpe();
            }

            var10002 = this.dataStrList;
            if (var10002 == null) {
               Intrinsics.throwNpe();
            }

            var6[i] = Float.parseFloat(var10002[i]);
         }

         float[] var10001 = this.data;
         if (var10001 == null) {
            Intrinsics.throwNpe();
         }

         this.intervalNumHeart = var10001.length;
         this.intervalRowHeart = this.mWidth / (float)this.intervalNumHeart;
         this.intervalColumnHeart = this.mHeight / (this.MAX_VALUE * (float)2);
      } else {
         float var7;
         DensityUtil var8;
         Context var10003;
         if (var2 == this.SHOW_MODEL_DYNAMIC_SCROLL) {
            var10000 = this.dataStrList;
            if (var10000 == null) {
               Intrinsics.throwNpe();
            }

            dataLength = var10000.length;
            this.data = new float[dataLength];
            i = 0;

            for(var4 = dataLength; i < var4; ++i) {
               var6 = this.data;
               if (var6 == null) {
                  Intrinsics.throwNpe();
               }

               var10002 = this.dataStrList;
               if (var10002 == null) {
                  Intrinsics.throwNpe();
               }

               var6[i] = Float.parseFloat(var10002[i]);
            }

            var7 = this.mWidth;
            var8 = DensityUtil.INSTANCE;
            var10003 = this.getContext();
            Intrinsics.checkExpressionValueIsNotNull(var10003, "context");
            this.intervalRowHeart = var7 / (float)var8.dip2px(var10003, this.INTERVAL_SCROLL_REFRESH);
            this.intervalNumHeart = (int)(this.mWidth / this.intervalRowHeart);
            this.intervalColumnHeart = this.mHeight / (this.MAX_VALUE * (float)2);
            this.startScrollTimer();
         } else if (var2 == this.SHOW_MODEL_DYNAMIC_REFRESH) {
            var7 = this.mWidth;
            var8 = DensityUtil.INSTANCE;
            var10003 = this.getContext();
            Intrinsics.checkExpressionValueIsNotNull(var10003, "context");
            this.intervalRowHeart = var7 / (float)var8.dip2px(var10003, this.INTERVAL_SCROLL_REFRESH);
            this.intervalNumHeart = (int)(this.mWidth / this.intervalRowHeart);
            this.intervalColumnHeart = this.mHeight / (this.MAX_VALUE * (float)2);
         }
      }

   }

   private final void startScrollTimer() {
      this.timer = new Timer();
      this.timerTask = (TimerTask)(new TimerTask() {
         public void run() {
            int var10000 = EcgShoeView.this.scrollIndex;
            float[] var10001 = EcgShoeView.this.data;
            if (var10001 == null) {
               Intrinsics.throwNpe();
            }

            if (var10000 < var10001.length) {
               EcgShoeView var2 = EcgShoeView.this;
               var2.scrollIndex = var2.scrollIndex + 1;
            } else {
               EcgShoeView.this.scrollIndex = 0;
            }

         }
      });
      Timer var10000 = this.timer;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("timer");
      }

      TimerTask var10001 = this.timerTask;
      if (var10001 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("timerTask");
      }

      var10000.schedule(var10001, 0L, 50L);
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      Timer var10000 = this.timer;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("timer");
      }

      var10000.cancel();
   }

   public EcgShoeView(@NotNull Context context, @NotNull AttributeSet attrs) {
      super(context,attrs);
      Intrinsics.checkParameterIsNotNull(context, "context");
      Intrinsics.checkParameterIsNotNull(attrs, "attrs");
      this.SHOW_MODEL_DYNAMIC_SCROLL = 1;
      this.SHOW_MODEL_DYNAMIC_REFRESH = 2;
      this.INTERVAL_SCROLL_REFRESH = 80.0F;
      this.MAX_VALUE = 20.0F;
      this.HEART_LINE_STROKE_WIDTH = 2.5F;
      this.GRID_LINE_STROKE_WIDTH = 1.5F;
      this.GRID_WIDTH_AND_HEIGHT = 5.0F;
      this.init();
   }

   // $FF: synthetic method
   public static final void access$setData$p(EcgShoeView $this, float[] var1) {
      $this.data = var1;
   }

   public View _$_findCachedViewById(int var1) {
      if (this._$_findViewCache == null) {
         this._$_findViewCache = new HashMap();
      }

      View var2 = (View)this._$_findViewCache.get(var1);
      if (var2 == null) {
         var2 = this.findViewById(var1);
         this._$_findViewCache.put(var1, var2);
      }

      return var2;
   }

   public void _$_clearFindViewByIdCache() {
      if (this._$_findViewCache != null) {
         this._$_findViewCache.clear();
      }

   }
}
