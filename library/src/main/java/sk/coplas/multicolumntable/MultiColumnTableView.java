package sk.coplas.multicolumntable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Multicolumn table view
 * TODO documentation
 */
public class MultiColumnTableView extends FrameLayout {

    private String[] titles;
    private String[][] values;

    private TableLayout fixed;
    private TableLayout floating;
    private int fixedCount = 1;

    private LayoutInflater inflater;

    public MultiColumnTableView(Context context) {
        super(context);
        init(null, 0);
    }

    public MultiColumnTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MultiColumnTableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.container, this, true);

        fixed = (TableLayout)findViewById(R.id.fixed);
        floating = (TableLayout)findViewById(R.id.floating);

        redraw();
    }

    private void redraw() {
        if (titles != null && values != null) {
            redrawFixed();
            redrawFloating();
        }
    }

    private void redrawFixed() {
        fixed.removeAllViews();
        TableRow row;
        TextView item;
        row = (TableRow) inflater.inflate(R.layout.row, fixed, false);
        for (int i = 0; i < fixedCount; i++) {
            item = (TextView) inflater.inflate(R.layout.item, row, false);
            if (i < titles.length) {
                item.setText(titles[i]);
            }
            row.addView(item);
        }
        fixed.addView(row);
        for (String[] value : values) {
            row = (TableRow) inflater.inflate(R.layout.row, fixed, false);
            for (int i = 0; i < fixedCount; i++) {
                item = (TextView) inflater.inflate(R.layout.item, row, false);
                if (i < value.length) {
                    item.setText(value[i]);
                }
                row.addView(item);
            }
            fixed.addView(row);
        }
    }

    private void redrawFloating() {
        floating.removeAllViews();
        TableRow row;
        TextView item;
        row = (TableRow) inflater.inflate(R.layout.row, floating, false);
        for (int i = fixedCount; i < titles.length; i++) {
            item = (TextView) inflater.inflate(R.layout.item, row, false);
            item.setText(titles[i]);
            row.addView(item);
        }
        floating.addView(row);
        for (String[] value : values) {
            row = (TableRow) inflater.inflate(R.layout.row, floating, false);
            for (int i = fixedCount; i < titles.length; i++) {
                item = (TextView) inflater.inflate(R.layout.item, row, false);
                if (i < value.length) {
                    item.setText(value[i]);
                }
                row.addView(item);
            }
            floating.addView(row);
        }
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        Log.d("mctv", titles.toString());
        redraw();
    }

    public void setValues(String[][] values) {
        this.values = values;
        Log.d("mctv", values.toString());
        redraw();
    }

    public void setFixedCount(int fixedCount) {
        this.fixedCount = fixedCount;
        redraw();
    }
}
