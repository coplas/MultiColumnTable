package sk.coplas.multicolumntable.sample;

import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * Sample for MultiColumnTableView
 */
public class CustomStyledFragment extends AbstractFragment {

    public CustomStyledFragment() {
    }

    @Override
    protected ViewGroup getView(LayoutInflater inflater, ViewGroup container) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_custom, container, false);
    }

}
