package theysaidso.com.theysaidso.Runnable;

import android.widget.TextView;

import theysaidso.com.theysaidso.Entity.Quote;
import theysaidso.com.theysaidso.HttpController.CustomRunnable;
import theysaidso.com.theysaidso.TextUtils.TextViewSpan;

/**
 * Created by chrisyu on 15/12/22.
 */
public class QuoteRunnable extends CustomRunnable{
    private TextView authorTextView, quoteTextView;

    public QuoteRunnable (TextView authorTextView, TextView quoteTextView) {
        this.authorTextView = authorTextView;
        this.quoteTextView = quoteTextView;
    }

    @Override
    public void run() {
        String author = ((Quote)this.data).getAuthor();
        String quote = ((Quote)this.data).getQuote();
        authorTextView.setText(author);
        //quoteTextView.setText(quote);
        TextViewSpan.work(quoteTextView, quote, -1);
    }
}
