package years.im.ultimaterecyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = new HelloFragment();
        fragment.setArguments(getIntent().getExtras());
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }
}
