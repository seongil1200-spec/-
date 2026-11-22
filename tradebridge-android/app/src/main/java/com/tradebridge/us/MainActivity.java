package com.tradebridge.us;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.browser.customtabs.CustomTabsIntent;
import java.util.Locale;

public class MainActivity extends Activity {
    private final int navy = Color.rgb(8, 30, 46);
    private final int teal = Color.rgb(0, 143, 132);
    private LinearLayout column;
    private boolean korean;
    private int dp(int n) { return (int) (n * getResources().getDisplayMetrics().density + .5f); }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        korean = "ko".equals(Locale.getDefault().getLanguage());
        getWindow().setStatusBarColor(navy);
        getWindow().setNavigationBarColor(navy);
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(245, 249, 250));
        scroll.setFillViewport(true);
        column = new LinearLayout(this);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setPadding(dp(24), dp(32), dp(24), dp(24));
        column.setGravity(Gravity.CENTER_HORIZONTAL);
        scroll.addView(column);
        label("TradeBridge US", 28, navy, 18);
        label(korean ? "미국 건설·기술직 구인구직" : "Construction & Skilled Trades Jobs", 18, navy, 8);
        label(korean ? "미국 전역의 기술 인력과 채용 기업을 연결합니다." :
              "Connect with skilled workers and employers across the United States.", 14, Color.DKGRAY, 16);
        action(korean ? "구인구직 사이트 열기" : "Find Work", UrlPolicy.HOME, true);
        action(korean ? "로그인 / 회원가입" : "Sign in / Create Account", UrlPolicy.page("signin-with-chatgpt?return_to=/"), false);
        action(korean ? "기업 채용" : "Hire Talent", UrlPolicy.HOME, false);
        label(korean ? "지원 및 계정 안내" : "Help & Account", 17, navy, 16);
        action(korean ? "고객지원" : "Help & Support", UrlPolicy.page("support?lang=en"), false);
        action(korean ? "개인정보처리방침" : "Privacy Policy", UrlPolicy.page("privacy?lang=en"), false);
        action(korean ? "이용약관" : "Terms of Use", UrlPolicy.page("terms?lang=en"), false);
        action(korean ? "계정 삭제" : "Delete Your Account", UrlPolicy.page("delete-account?lang=en"), false);
        setContentView(scroll);
    }
    private void label(String value, int size, int color, int top) {
        TextView label = new TextView(this);
        label.setText(value);
        label.setGravity(Gravity.CENTER);
        label.setTextSize(size);
        label.setTextColor(color);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
        lp.topMargin = dp(top);
        column.addView(label, lp);
    }
    private void action(String title, String url, boolean primary) {
        Button b = new Button(this);
        b.setText(title);
        b.setAllCaps(false);
        b.setTextColor(primary ? Color.WHITE : navy);
        if (primary) b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(teal));
        b.setOnClickListener(v -> openBrowser(url));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,dp(54));
        lp.topMargin = dp(9);
        column.addView(b,lp);
    }
    private void openBrowser(String url) {
        if (!UrlPolicy.isTrusted(url)) return;
        Uri uri = Uri.parse(url);
        try {
            CustomTabsIntent tabs = new CustomTabsIntent.Builder()
                .setShowTitle(true).setToolbarColor(navy).build();
            tabs.launchUrl(this, uri);
        } catch (ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
