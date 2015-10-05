package jpinkidu.mytodolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by pinkilu on 05.10.2015.
 */
public class ListFragment extends android.app.ListFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list,container, false);
        String[] dataSrc = {"1","Ste"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_list_items, R.id.newTaskValue, dataSrc);
        return null;
    }
}
