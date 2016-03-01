package com.tanyadong.testapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInstaller;

import java.util.HashMap;

/**
 * Created by michaelknight123 on 2/13/2016.
 */
public class SessionManager
{

    SharedPreferences pref;
    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PrefName = "TanyaDongPref";
    private static final String IsLogin = "IsLoggedIn";
    private static final String KeyName = "name";
    private static final String KeySurname = "surname";
    private static final String KeyAge = "age";
    private static final String KeyEmail = "email";
    private static final String KeyUsername = "username";
    private static final String KeyUserID = "userId";

    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PrefName, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String surname, String age, String email, String username, String userId)
    {
        editor.putBoolean(IsLogin, true);
        editor.putString(KeyName, name);
        editor.putString(KeySurname, surname);
        editor.putString(KeyAge, age);
        editor.putString(KeyEmail, email);
        editor.putString(KeyUsername, username);
        editor.putString(KeyUserID, userId);
        editor.commit();
    }

    public String getUsername()
    {
        return pref.getString(KeyUsername, "");
    }

    public String getUserID() { return pref.getString(KeyUserID, ""); }

    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KeyName, pref.getString(KeyName, null));
        user.put(KeySurname, pref.getString(KeySurname, null));
        user.put(KeyAge, pref.getString(KeyAge, null));
        user.put(KeyEmail, pref.getString(KeyEmail, null));
        user.put(KeyUsername, pref.getString(KeyUsername, null));
        user.put(KeyUserID, pref.getString(KeyUserID, null));
        return user;
    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(IsLogin, false);
    }

    public void checkLogin()
    {
        if(!this.isLoggedIn())
        {
            Intent i = new Intent(_context, RegisterActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void logoutUser()
    {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

}
