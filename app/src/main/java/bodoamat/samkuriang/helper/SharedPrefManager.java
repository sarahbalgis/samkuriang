package bodoamat.samkuriang.helper;

import android.content.Context;
import android.content.SharedPreferences;

import bodoamat.samkuriang.models.Customer;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";

    private static final String KEY_CUSTOMERS_ID = "keycustomersid";
    private static final String KEY_CUSTOMERS_NAME = "keycustomersname";
//    private static final String KEY_CUSTOMERS_PASSWORD = "keycustomerspassword";
    private static final String KEY_CUSTOMERS_EMAIL = "keycustomersemail";;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean loginCustomer(Customer customer) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CUSTOMERS_ID, customer.getId());
        editor.putString(KEY_CUSTOMERS_NAME, customer.getName());
//        editor.putString(KEY_CUSTOMERS_PASSWORD, customer.getPassword());
        editor.putString(KEY_CUSTOMERS_EMAIL, customer.getEmail());
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_CUSTOMERS_EMAIL, null) != null)
            return true;
        return false;
    }

    public Customer getCustomer() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Customer(
                sharedPreferences.getInt(KEY_CUSTOMERS_ID, 0),
                sharedPreferences.getString(KEY_CUSTOMERS_NAME, null),
//                sharedPreferences.getString(KEY_CUSTOMERS_PASSWORD, null),
                sharedPreferences.getString(KEY_CUSTOMERS_EMAIL, null)

        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
