package jpinkidu.mytodolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mArrayToDoList;
    private ArrayAdapter<String> mStringArrayAdapter;
    private EditText mNewItem;
    private Button mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mArrayToDoList = new ArrayList<String>();
        mStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayToDoList);
        ListView listViewMain = (ListView) findViewById(R.id.listViewMain);
        listViewMain.setAdapter(mStringArrayAdapter);

        //see onCreateContextMenu
        //see onCreateContextMenu
        registerForContextMenu(listViewMain);
        try {
            Log.i("On Create", "on Create");
            Scanner scanner = new Scanner(openFileInput("ToDoList.txt"));
            while (scanner.hasNextLine()){
                String todoList = scanner.nextLine();
                mStringArrayAdapter.add(todoList);
            }
        }catch (Exception e){
            Log.i("On Create", e.getMessage());
        }

    }



    public void addNewItemToList(View v){
        EditText mNewItem = (EditText) findViewById(R.id.newItem);
        String toDoItem =  mNewItem.getText().toString().trim();
        if (toDoItem.isEmpty()){
            Toast.makeText(getApplicationContext(), "No text", Toast.LENGTH_SHORT).show();
            return;
        }
        mStringArrayAdapter.add(toDoItem);
        mNewItem.setText("");

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() != R.id.listViewMain){
            return;
        }
        menu.setHeaderTitle("Options");
        String[] options = {"Delete", "Return"};
        for (String option: options){
            menu.add(option);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectIndex = info.position;
        if (item.getTitle().equals("Delete")){
            mArrayToDoList.remove(selectIndex);
            mStringArrayAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Item removed ", Toast.LENGTH_SHORT).show();
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        try {
            Log.i("back pressed", "on back pressed event");
            PrintWriter pw = new PrintWriter(openFileOutput("ToDoList.txt", Context.MODE_PRIVATE));
            for (String toDoList: mArrayToDoList) {
                pw.println(toDoList);
            }
            pw.close();
        }catch (Exception e){
            Log.i("back pressed", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
