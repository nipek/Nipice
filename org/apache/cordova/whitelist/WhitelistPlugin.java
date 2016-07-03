package org.apache.cordova.whitelist;

import android.content.Context;
import android.util.Log;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.Whitelist;
import org.xmlpull.v1.XmlPullParser;

public class WhitelistPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "WhitelistPlugin";
    private Whitelist allowedIntents;
    private Whitelist allowedNavigations;
    private Whitelist allowedRequests;

    private class CustomConfigXmlParser extends ConfigXmlParser {
        final /* synthetic */ WhitelistPlugin this$0;

        private CustomConfigXmlParser(WhitelistPlugin whitelistPlugin) {
            WhitelistPlugin whitelistPlugin2 = whitelistPlugin;
            this.this$0 = whitelistPlugin2;
        }

        /* synthetic */ CustomConfigXmlParser(WhitelistPlugin whitelistPlugin, C00401 c00401) {
            C00401 x1 = c00401;
            this(whitelistPlugin);
        }

        public void handleStartTag(XmlPullParser xmlPullParser) {
            CustomConfigXmlParser this = this;
            XmlPullParser xml = xmlPullParser;
            String strNode = xml.getName();
            if (strNode.equals("content")) {
                this.this$0.allowedNavigations.addWhiteListEntry(xml.getAttributeValue(null, "src"), false);
            } else if (strNode.equals("allow-navigation")) {
                origin = xml.getAttributeValue(null, "href");
                if ("*".equals(origin)) {
                    this.this$0.allowedNavigations.addWhiteListEntry("http://*/*", false);
                    this.this$0.allowedNavigations.addWhiteListEntry("https://*/*", false);
                    this.this$0.allowedNavigations.addWhiteListEntry("data:*", false);
                } else {
                    this.this$0.allowedNavigations.addWhiteListEntry(origin, false);
                }
            } else if (strNode.equals("allow-intent")) {
                this.this$0.allowedIntents.addWhiteListEntry(xml.getAttributeValue(null, "href"), false);
            } else if (strNode.equals("access")) {
                origin = xml.getAttributeValue(null, "origin");
                String subdomains = xml.getAttributeValue(null, "subdomains");
                boolean external = xml.getAttributeValue(null, "launch-external") != null;
                if (origin == null) {
                    return;
                }
                Whitelist access$200;
                String str;
                boolean z;
                if (external) {
                    int w = Log.w(WhitelistPlugin.LOG_TAG, "Found <access launch-external> within config.xml. Please use <allow-intent> instead.");
                    access$200 = this.this$0.allowedIntents;
                    str = origin;
                    z = subdomains != null && subdomains.compareToIgnoreCase("true") == 0;
                    access$200.addWhiteListEntry(str, z);
                } else if ("*".equals(origin)) {
                    this.this$0.allowedRequests.addWhiteListEntry("http://*/*", false);
                    this.this$0.allowedRequests.addWhiteListEntry("https://*/*", false);
                } else {
                    access$200 = this.this$0.allowedRequests;
                    str = origin;
                    z = subdomains != null && subdomains.compareToIgnoreCase("true") == 0;
                    access$200.addWhiteListEntry(str, z);
                }
            }
        }

        public void handleEndTag(XmlPullParser xml) {
        }
    }

    public WhitelistPlugin() {
        CordovaPlugin cordovaPlugin = this;
    }

    public WhitelistPlugin(Context context) {
        Context context2 = context;
        Whitelist whitelist = r6;
        Whitelist whitelist2 = new Whitelist();
        whitelist2 = r6;
        Whitelist whitelist3 = new Whitelist();
        this(whitelist, whitelist2, null);
        CustomConfigXmlParser customConfigXmlParser = r6;
        CustomConfigXmlParser customConfigXmlParser2 = new CustomConfigXmlParser();
        customConfigXmlParser.parse(context2);
    }

    public WhitelistPlugin(XmlPullParser xmlPullParser) {
        XmlPullParser xmlParser = xmlPullParser;
        Whitelist whitelist = r6;
        Whitelist whitelist2 = new Whitelist();
        whitelist2 = r6;
        Whitelist whitelist3 = new Whitelist();
        this(whitelist, whitelist2, null);
        CustomConfigXmlParser customConfigXmlParser = r6;
        CustomConfigXmlParser customConfigXmlParser2 = new CustomConfigXmlParser();
        customConfigXmlParser.parse(xmlParser);
    }

    public WhitelistPlugin(Whitelist whitelist, Whitelist whitelist2, Whitelist whitelist3) {
        Whitelist allowedNavigations = whitelist;
        Whitelist allowedIntents = whitelist2;
        Whitelist allowedRequests = whitelist3;
        CordovaPlugin cordovaPlugin = this;
        if (allowedRequests == null) {
            Whitelist whitelist4 = r7;
            Whitelist whitelist5 = new Whitelist();
            allowedRequests = whitelist4;
            allowedRequests.addWhiteListEntry("file:///*", false);
            allowedRequests.addWhiteListEntry("data:*", false);
        }
        this.allowedNavigations = allowedNavigations;
        this.allowedIntents = allowedIntents;
        this.allowedRequests = allowedRequests;
    }

    public void pluginInitialize() {
        if (this.allowedNavigations == null) {
            Whitelist whitelist = r5;
            Whitelist whitelist2 = new Whitelist();
            this.allowedNavigations = whitelist;
            whitelist = r5;
            whitelist2 = new Whitelist();
            this.allowedIntents = whitelist;
            whitelist = r5;
            whitelist2 = new Whitelist();
            this.allowedRequests = whitelist;
            CustomConfigXmlParser customConfigXmlParser = r5;
            WhitelistPlugin whitelistPlugin = this;
            CustomConfigXmlParser customConfigXmlParser2 = new CustomConfigXmlParser();
            customConfigXmlParser.parse(this.webView.getContext());
        }
    }

    public Boolean shouldAllowNavigation(String str) {
        if (this.allowedNavigations.isUrlWhiteListed(str)) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    public Boolean shouldAllowRequest(String str) {
        String url = str;
        if (Boolean.TRUE == shouldAllowNavigation(url)) {
            return Boolean.valueOf(true);
        }
        if (this.allowedRequests.isUrlWhiteListed(url)) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    public Boolean shouldOpenExternalUrl(String str) {
        if (this.allowedIntents.isUrlWhiteListed(str)) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    public Whitelist getAllowedNavigations() {
        return this.allowedNavigations;
    }

    public void setAllowedNavigations(Whitelist whitelist) {
        Whitelist whitelist2 = whitelist;
        this.allowedNavigations = whitelist2;
    }

    public Whitelist getAllowedIntents() {
        return this.allowedIntents;
    }

    public void setAllowedIntents(Whitelist whitelist) {
        Whitelist whitelist2 = whitelist;
        this.allowedIntents = whitelist2;
    }

    public Whitelist getAllowedRequests() {
        return this.allowedRequests;
    }

    public void setAllowedRequests(Whitelist whitelist) {
        Whitelist whitelist2 = whitelist;
        this.allowedRequests = whitelist2;
    }
}
