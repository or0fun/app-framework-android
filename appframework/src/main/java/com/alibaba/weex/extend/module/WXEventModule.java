package com.alibaba.weex.extend.module;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;


public class WXEventModule extends WXModule {

  private static final String WEEX_CATEGORY = "com.taobao.android.intent.category.WEEX";

  @WXModuleAnno(moduleMethod = true,runOnUIThread = true)
  public void openURL(String url) {
    if (TextUtils.isEmpty(url)) {
      return;
    }
    Intent intent = new Intent(Intent.ACTION_VIEW);
    String scheme = Uri.parse(url).getScheme();
    StringBuilder builder = new StringBuilder();
    if (TextUtils.equals("http", scheme) || TextUtils.equals("https", scheme)) {
      intent.putExtra("isLocal", false);
    }else if (TextUtils.equals("file", scheme)) {
      intent.putExtra("isLocal", true);
    } else {
      builder.append("http:");
    }
    builder.append(url);

    Uri uri = Uri.parse(builder.toString());
    intent.setData(uri);
    intent.addCategory(WEEX_CATEGORY);
    mWXSDKInstance.getContext().startActivity(intent);
  }
}
