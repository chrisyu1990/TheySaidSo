package theysaidso.com.theysaidso.TextUtils;

/**
 * Created by chrisyu on 15/12/22.
 */
public class FullCharConverter {
    public static String work(String str){
        for(char c:str.toCharArray()){
            str = str.replaceAll("　", " ");
            if((int)c >= 65281 && (int)c <= 65374){
                str = str.replace(c, (char)(((int)c)-65248));
            }
        }
        return str;
    }
}
