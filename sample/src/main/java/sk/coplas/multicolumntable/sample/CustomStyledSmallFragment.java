package sk.coplas.multicolumntable.sample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Locale;


/**
 * Sample for MultiColumnTableView
 */
public class CustomStyledSmallFragment extends AbstractFragment {

    public CustomStyledSmallFragment() {
    }

    @Override
    protected ViewGroup getView(LayoutInflater inflater, ViewGroup container) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_custom, container, false);
    }

    @Override
    protected void initData() {
        String[] titles = {"State", "AK"};
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
}
