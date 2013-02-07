package jp.s5r;

import com.uphyca.ServiceLocator;

import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Object statusBarService = ServiceLocator.getServiceStub(
                "statusbar",
                "com.android.internal.statusbar.IStatusBarService$Stub");

        Method method;
        try {
            method = statusBarService.getClass().getMethod(
                    "toggleRecentApps", null);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            method.invoke(statusBarService, null);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        finish();
    }
}
