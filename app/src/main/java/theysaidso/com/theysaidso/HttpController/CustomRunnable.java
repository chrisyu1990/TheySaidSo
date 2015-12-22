package theysaidso.com.theysaidso.HttpController;

/**
 * Created by chrisyu on 15/12/10.
 */
public abstract class CustomRunnable<T> implements Runnable {
    public T data;
    public String url;

    @Override
    abstract public void run();
}
