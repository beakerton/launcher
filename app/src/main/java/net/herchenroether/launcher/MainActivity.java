package net.herchenroether.launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends Activity {
    private final List<AppDetails> mApps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadApps();
        loadListView();
        addClickListener();
    }

    private void loadApps(){
        final PackageManager manager = getPackageManager();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        final List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            final AppDetails app = new AppDetails(ri.loadLabel(manager),
                    ri.activityInfo.packageName,
                    ri.activityInfo.loadIcon(manager));
            mApps.add(app);
        }
    }

    private void loadListView(){
        final ListView list = (ListView)findViewById(R.id.apps_list);

        ArrayAdapter<AppDetails> adapter = new ArrayAdapter<AppDetails>(this,
                R.layout.app_list_item,
                mApps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.app_list_item, null);
                }

                final ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
                appIcon.setImageDrawable(mApps.get(position).getAppIcon());

                final TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
                appLabel.setText(mApps.get(position).getLabel());

                final TextView appName = (TextView)convertView.findViewById(R.id.item_app_name);
                appName.setText(mApps.get(position).getPackageName());

                return convertView;
            }
        };

        list.setAdapter(adapter);
    }

    private void addClickListener(){
        final ListView list = (ListView)findViewById(R.id.apps_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                final PackageManager manager = getPackageManager();
                final Intent intent = manager.getLaunchIntentForPackage(mApps.get(pos).getPackageName().toString());
                startActivity(intent);
            }
        });
    }
}
