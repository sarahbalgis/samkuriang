package bodoamat.samkuriang.storage;

import android.content.Context;
import android.content.SharedPreferences;

import bodoamat.samkuriang.models.Customer;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "data";

    private static final String KEY_CUSTOMERS_ID = "id";
    private static final String KEY_CUSTOMERS_NAME = "name";
    private static final String KEY_CUSTOMERS_EMAIL = "email";
    private static final String KEY_CUSTOMERS_ADDRESS = "address";
    private static final String KEY_CUSTOMERS_PHONE_NUMBER = "phone_number";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void loginCustomer(Customer customer) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CUSTOMERS_ID, customer.getId());
        editor.putString(KEY_CUSTOMERS_NAME, customer.getName());
        editor.putString(KEY_CUSTOMERS_EMAIL, customer.getEmail());
        editor.putString(KEY_CUSTOMERS_ADDRESS, customer.getAddress());
        editor.putString(KEY_CUSTOMERS_PHONE_NUMBER, customer.getPhone_number());
        editor.commit();
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CUSTOMERS_EMAIL, null) != null;
    }

    public Customer getCustomer() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Customer(
                sharedPreferences.getInt(KEY_CUSTOMERS_ID, 0),
                sharedPreferences.getString(KEY_CUSTOMERS_NAME, null),
                sharedPreferences.getString(KEY_CUSTOMERS_EMAIL, null),
                sharedPreferences.getString(KEY_CUSTOMERS_ADDRESS, null),
                sharedPreferences.getString(KEY_CUSTOMERS_PHONE_NUMBER, null)
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
