package pl.marchuck.delegatesexpandableadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.marchuck.delegatesexpandableadapter.adapter.DelegatesAdapter;
import pl.marchuck.delegatesexpandableadapter.delegate.DelegatesManager;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableChild;
import pl.marchuck.delegatesexpandableadapter.iFacesAndAbstrctClases.BindableParent;
import pl.marchuck.delegatesexpandableadapter.impl.child.BlueChild;
import pl.marchuck.delegatesexpandableadapter.impl.child.PurpleChild;
import pl.marchuck.delegatesexpandableadapter.impl.child.YellowChild;
import pl.marchuck.delegatesexpandableadapter.impl.parent.BlueParent;
import pl.marchuck.delegatesexpandableadapter.impl.parent.PurpleParent;
import pl.marchuck.delegatesexpandableadapter.impl.parent.YellowParent;
import pl.marchuck.delegatesexpandableadapter.model.child.BlueSeba;
import pl.marchuck.delegatesexpandableadapter.model.child.PurpleSphinx;
import pl.marchuck.delegatesexpandableadapter.model.child.YellowKitty;
import pl.marchuck.delegatesexpandableadapter.model.parent.BlueHusky;
import pl.marchuck.delegatesexpandableadapter.model.parent.PurpleSOI;
import pl.marchuck.delegatesexpandableadapter.model.parent.YellowYork;
import pl.marchuck.delegatesexpandableadapter.utils.ExpandableBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

        List<BindableParent> dataSet = new ArrayList<>();
        List<BindableChild> children3 = new ArrayList<>();
        List<BindableChild> children2 = new ArrayList<>();
        List<BindableChild> children1 = new ArrayList<>();
        children1.add(new YellowKitty());
        children1.add(new PurpleSphinx());
        children1.add(new YellowKitty());

        children2.add(new BlueSeba());

        children3.add(new PurpleSphinx());
        children3.add(new YellowKitty());
        children3.add(new BlueSeba());


        dataSet.add(new BlueHusky("Pieseł", children1));
        dataSet.add(new YellowYork("Stonoga", children2));
        dataSet.add(new BlueHusky("Pudzian", children3));

        dataSet.add(new BlueHusky("Pieseł", children1));
        dataSet.add(new YellowYork("Stonoga", children2));
        dataSet.add(new BlueHusky("Pudzian", children3));

        dataSet.add(new BlueHusky("Pieseł", children1));
        dataSet.add(new YellowYork("Stonoga", children2));
        dataSet.add(new BlueHusky("Pudzian", children3));

        dataSet.add(new BlueHusky("Pieseł", children1));
        dataSet.add(new PurpleSOI("Pieseł", children2));
        dataSet.add(new PurpleSOI("Pieseł", children2));
        dataSet.add(new PurpleSOI("Pieseł", children2));
        dataSet.add(new PurpleSOI("Pieseł", children2));
        dataSet.add(new PurpleSOI("Pieseł", children2));


        dataSet.add(new BlueHusky("Pudzian", children3));

        dataSet.add(new BlueHusky("Pieseł", children1));
        dataSet.add(new YellowYork("Stonoga", children2));
        dataSet.add(new BlueHusky("Pudzian", children3));
        Collections.shuffle(dataSet);


        DelegatesManager delegatesManager = new DelegatesManager(dataSet);
        //register all delegates which you are using
        delegatesManager.addDelegateParent(new YellowParent());
        delegatesManager.addDelegateParent(new BlueParent());
        delegatesManager.addDelegateParent(new PurpleParent());

        delegatesManager.addDelegateChild(new YellowChild());
        delegatesManager.addDelegateChild(new BlueChild());
        delegatesManager.addDelegateChild(new PurpleChild());


        delegatesManager.sync();

        DelegatesAdapter adapter = new DelegatesAdapter(dataSet, delegatesManager);

        new ExpandableBuilder()
                .withAdapter(adapter)
                .withContext(MainActivity.this)
                .withRecyclerView(recyclerView)
                .withSavedInstanceState(savedInstanceState)
                .build();


//            }
//        });
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
