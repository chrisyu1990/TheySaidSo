package theysaidso.com.theysaidso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import theysaidso.com.theysaidso.Entity.Quote;
import theysaidso.com.theysaidso.HttpController.CustomCallBack;
import theysaidso.com.theysaidso.HttpController.HttpClient;
import theysaidso.com.theysaidso.Runnable.QuoteRunnable;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.qoute) TextView qoute;
    @Bind(R.id.author) TextView author;
    @Bind(R.id.root)
    View root;

    CustomCallBack httpCallBack;
    QuoteRunnable quoteRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/kelson.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        quoteRunnable = new QuoteRunnable(author, qoute);
        httpCallBack =  new CustomCallBack(Quote.class, quoteRunnable, this);
        HttpClient.makeRequest("http://quotes.stormconsultancy.co.uk/random.json", httpCallBack);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient.makeRequest("http://quotes.stormconsultancy.co.uk/random.json", httpCallBack);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
