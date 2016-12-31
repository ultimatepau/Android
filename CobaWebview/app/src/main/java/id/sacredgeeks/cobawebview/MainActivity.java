package id.sacredgeeks.cobawebview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pBar ;
    private WebView view;
    FloatingActionButton item1,item2,item3,item4;
    FloatingActionMenu menu;
    private SwipeRefreshLayout swl;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        swl = (SwipeRefreshLayout)findViewById(R.id.swipe);

        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                openweb(uri);
            }
        });

        openweb("http://google.co.id");
        //uri = "http://google.co.id";
        //openweb("http://192.168.101.1/login");
    }

    public void openweb(String url) {
        if (swl.isRefreshing()) {
            swl.setRefreshing(false);
        }
        view = (WebView) findViewById(R.id.webview);

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);

        view.setWebChromeClient(new MyWebChromeClient());
        view.setWebViewClient(new WebViewClient());
        //view.setWebViewClient(new MyBrowser());
        view.loadUrl(url);

        uri = url;

    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (progress == 100) {
                pBar = (ProgressBar) findViewById(R.id.pbar);
                pBar.setVisibility(View.GONE);
            }
        }

    }

    boolean about = false;


    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Toast.makeText(getApplicationContext(),"Dont Close Me !!!",Toast.LENGTH_SHORT).show();
            // activity switch stuff..
            return true;
        }
        return false;
    }
    */

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        FloatingActionMenu fam = (FloatingActionMenu)findViewById(R.id.material_design_android_floating_action_menu);
        if(fam.getVisibility() == View.VISIBLE){
            Toast.makeText(this, "Kamu menekan tombol kembali! Jangan kemana mana!", Toast.LENGTH_SHORT).show();
        } else {
            if (doubleBackToExitPressedOnce) {
                fam.setVisibility(View.VISIBLE);
                fam.close(false);
            } else {
                Toast.makeText(this, "Silahkan sentuh lagi tombol kembali untuk memunculkan menu", Toast.LENGTH_SHORT).show();
            }
            this.doubleBackToExitPressedOnce = true;

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void openlogin(View view) {
        openweb("http://youtube.com");
        //openweb("http://192.168.101.1/login");
        FloatingActionMenu fam = (FloatingActionMenu)findViewById(R.id.material_design_android_floating_action_menu);
        fam.close(true);
    }

    public void openedubox(View view) {
        openweb("http://ultimatepau.pe.hu");
        //openweb("http://edubox.smkn4bdg.sch.id");
        FloatingActionMenu fam = (FloatingActionMenu)findViewById(R.id.material_design_android_floating_action_menu);
        fam.close(true);
    }

    public void hidefab(View view) {
        FloatingActionMenu fam = (FloatingActionMenu)findViewById(R.id.material_design_android_floating_action_menu);
        fam.setVisibility(View.GONE);
        Toast.makeText(this, "Untuk memunculkan menu, tekan tombol kembali dua kali", Toast.LENGTH_SHORT).show();
    }

    public void aboutapp(View view) {
        about();
        FloatingActionMenu fam = (FloatingActionMenu)findViewById(R.id.material_design_android_floating_action_menu);
        fam.close(true);
    }

    public void about() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.about,null);
        builder.setView(dialogview);

        builder.setTitle("Tentang Edubox Mobile");
        builder.setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public class MyBrowser extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            pBar.setVisibility(View.GONE);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            pBar.setVisibility(View.VISIBLE);
            view.getSettings().setJavaScriptEnabled(true);
            view.loadUrl(url);
            return true;
        }
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //view.loadUrl("about:blank");
            Toast.makeText(MainActivity.this,"Konek wifi dulu bosque",Toast.LENGTH_SHORT).show();
        }
    }

}
