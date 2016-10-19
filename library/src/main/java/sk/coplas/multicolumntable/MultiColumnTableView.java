package sk.coplas.multicolumntable;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Multicolumn table view
 * TODO documentation
 */
public class MultiColumnTableView extends FrameLayout {

    private String[] titles;
    private String[][] values;
    private int[] aligns = null;

    private TableLayout fixed;
    private TableLayout floating;

    private LayoutInflater inflater;

    private int fixedCount = 1;
    private int item;

    private int headerTextAppearance;
    private int fixedTextAppearance;
    private int cellTextAppearance;
    private boolean strippedRows;
    private int stripColor;

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
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiColumnTableView, 0, 0);

        fixedCount = a.getInt(R.styleable.MultiColumnTableView_fixedCount, 1);
        item = a.getResourceId(R.styleable.MultiColumnTableView_cellView, R.layout.item);
        cellTextAppearance = a.getResourceId(R.styleable.MultiColumnTableView_cellTextAppearance, -1);
        fixedTextAppearance = a.getResourceId(R.styleable.MultiColumnTableView_fixedTextAppearance, -1);
        headerTextAppearance = a.getResourceId(R.styleable.MultiColumnTableView_headerTextAppearance, -1);
        strippedRows = a.getBoolean(R.styleable.MultiColumnTableView_strippedRows, false);
        stripColor = a.getColor(R.styleable.MultiColumnTableView_stripColor, getResources().getColor(R.color.colorStrip));

        a.recycle();


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
        int index = 0;
        fixed.removeAllViews();
        TextView item;
        TableRow row;
        row = getTableRow(fixed, index);
        for (int i = 0; i < fixedCount; i++) {
            item = getCellTextView(row, true, true);
            if (i < titles.length) {
                item.setText(titles[i]);
            }
            row.addView(item);
        }
        fixed.addView(row);
        index++;
        for (String[] value : values) {
            row = getTableRow(fixed, index);
            for (int i = 0; i < fixedCount; i++) {
                item = getCellTextView(row, false, true);
                if (i < value.length) {
                    item.setText(value[i]);
                    item.setGravity(getGravity(aligns, i));
                }
                row.addView(item);
            }
            fixed.addView(row);
            index++;
        }
    }

    private TableRow getTableRow(TableLayout tableLayout, int index) {
        TableRow row = (TableRow) inflater.inflate(R.layout.row, tableLayout, false);
        if (strippedRows && (index % 2 == 0)) {
            row.setBackgroundColor(stripColor);
        }
        return row;
    }

    private void redrawFloating() {
        int index = 0;
        floating.removeAllViews();
        TableRow row;
        TextView item;
        row = getTableRow(floating, index);
        for (int i = fixedCount; i < titles.length; i++) {
            item = getCellTextView(row, true, false);
            item.setText(titles[i]);
            row.addView(item);
        }
        floating.addView(row);
        index++;
        for (String[] value : values) {
            row = getTableRow(floating, index);
            for (int i = fixedCount; i < titles.length; i++) {
                item = getCellTextView(row, false, false);
                if (i < value.length) {
                    item.setText(value[i]);
                    item.setGravity(getGravity(aligns, i));
                }
                row.addView(item);
            }
            floating.addView(row);
            index++;
        }
    }

    private int getGravity(int[] aligns, int i) {
        if (aligns != null && aligns.length > i) {
            return aligns[i]|Gravity.CENTER_VERTICAL;
        }
        return Gravity.START|Gravity.CENTER_VERTICAL;
    }

    private TextView getCellTextView(TableRow row, boolean isHeader, boolean isFixed) {
        TextView item = (TextView) inflater.inflate(this.item, row, false);
        if (isHeader) {
            setTextAppearance(item, headerTextAppearance);
        } else if (isFixed) {
            setTextAppearance(item, fixedTextAppearance);
        } else {
            setTextAppearance(item, cellTextAppearance);
        }
        return item;
    }

    private void setTextAppearance(TextView item, int textAppearance) {
        if ( textAppearance > -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                item.setTextAppearance(textAppearance);
            } else {
                item.setTextAppearance(getContext(), textAppearance);
            }
        }
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        redraw();
    }

    public void setValues(String[][] values) {
        this.values = values;
        redraw();
    }

    public void setFixedCount(int fixedCount) {
        this.fixedCount = fixedCount;
        redraw();
    }

    public void setAligns(int[] aligns) {
        this.aligns = aligns;
        redraw();
    }
}
