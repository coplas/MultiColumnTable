package sk.coplas.multicolumntable.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import sk.coplas.multicolumntable.MultiColumnTableView;


/**
 * Sample for MultiColumnTableView
 */
public class AlignsFragment extends Fragment {

    private MultiColumnTableView table;

    protected String[] titles;
    protected String[][] data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = getView(inflater, container);
        table = (MultiColumnTableView) view.findViewById(R.id.table);
        initData();
        setupTable(titles, data);

        return view;
    }

    protected ViewGroup getView(LayoutInflater inflater, ViewGroup container) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_custom, container, false);
    }

    protected void initData() {
        String[] titles = {"State", "Title LEFT", "Title CENTER", "Title RIGHT"};
        String[] states = {"Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming",
                "District of Columbia",
                "Puerto Rico",
                "Guamv",
                "American Samoa",
                "U.S. Virgin Islands",
                "Northern Mariana Islands"};

        String[][] data = new String[states.length][titles.length];
        for (int i = 0; i < states.length; i++) {
            data[i][0] = states[i];
            for (int j = 1; j < titles.length; j++) {
                data[i][j] = String.format(Locale.getDefault(), "%d - %d", i, j-1);
            }
        }

        this.titles = titles;
        this.data = data;


    }

    private void setupTable(String[] titles, String[][] data) {
        table.setAligns(new int[]{Gravity.START, Gravity.START, Gravity.CENTER, Gravity.END});
        table.setTitles(titles);
        table.setValues(data);
    }
}
