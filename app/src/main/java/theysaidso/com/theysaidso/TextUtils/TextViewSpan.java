package theysaidso.com.theysaidso.TextUtils;

import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by chrisyu on 15/12/22.
 */
public class TextViewSpan {
    public static void work(final TextView textview, String string, int color) {
        textview.setText("");
        textview.append(string);

        Spannable spannableText = (Spannable) textview.getText();
        spannableText.setSpan(new ForegroundColorSpan(Color.BLACK), 0, string.length(), 0);

        spannableText.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, string.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //CusOGLL cusOGLL = new CusOGLL(textview);
        //textview.getViewTreeObserver().addOnGlobalLayoutListener(cusOGLL);
    }
        static class CusOGLL implements ViewTreeObserver.OnGlobalLayoutListener {
            private TextView tv;
            public CusOGLL(TextView tv){
                this.tv = tv;
            }
            @Override
            public void onGlobalLayout() {

                tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                Layout layout = tv.getLayout();
                int x = 0;
                if(true){
                    String text = tv.getText().toString();
                    String temp = "";
                    int start=0;
                    int end= 0;
                    for (int i=0; i<tv.getLineCount(); i++) {
                        end = layout.getLineEnd(i);
                        Log.i("yoyoyoy" + i, "yoyyoyoyoy" + " " + text.substring(start,end) + " ");
                        //temp += "  " + text.substring(start,end) + "  \";
                        start = end;
                    }

                    SpannableStringBuilder ssb = new SpannableStringBuilder();
                    ssb.append(temp);

                    ssb.setSpan(new ForegroundColorSpan(Color.BLACK), 0, temp.length(), 0);

                    ssb.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, temp.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tv.setText(ssb);

                    for (int i=0; i<tv.getLineCount(); i++) {
                        end = layout.getLineEnd(i);
                        Log.i("yoyoyoy" + i, "yoyyoyoyoy[" + " " + text.substring(start,end) + " ]");
                        temp += " " + text.substring(start,end) + " ";
                        start = end;
                    }
                }
            }
        }

}
