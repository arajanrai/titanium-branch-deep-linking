/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package io.branch.sdk;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.BranchShortLinkBuilder;
import io.branch.referral.Defines;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ShareSheetStyle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import org.json.JSONObject;


// This proxy can be created by calling TitaniumDeferredDeepLinkingSDK.createBranchUniversalObject({key: object, ...})
@Kroll.proxy(creatableInModule=TitaniumDeferredDeepLinkingSDKModule.class)
public class BranchUniversalObjectProxy extends TiViewProxy
{
	// Standard Debugging variables
	private static final String LCAT = "BranchUniversalObjectProxy";
	private static final boolean DBG = TiConfig.LOGD;

	private final BranchUniversalObject branchUniversalObject = new BranchUniversalObject();
	private final LinkProperties linkProperties = new LinkProperties();

	private class BranchUniversalObjectView extends TiUIView
	{
		public BranchUniversalObjectView(TiViewProxy proxy) {
			super(proxy);
			LayoutArrangement arrangement = LayoutArrangement.DEFAULT;

			if (proxy.hasProperty(TiC.PROPERTY_LAYOUT)) {
				String layoutProperty = TiConvert.toString(proxy.getProperty(TiC.PROPERTY_LAYOUT));
				if (layoutProperty.equals(TiC.LAYOUT_HORIZONTAL)) {
					arrangement = LayoutArrangement.HORIZONTAL;
				} else if (layoutProperty.equals(TiC.LAYOUT_VERTICAL)) {
					arrangement = LayoutArrangement.VERTICAL;
				}
			}
			setNativeView(new TiCompositeLayout(proxy.getActivity(), arrangement));
		}

		@Override
		public void processProperties(KrollDict d)
		{
			super.processProperties(d);
		}
	}


	// Constructor
	public BranchUniversalObjectProxy()
	{
		super();
	}

	@Override
	public TiUIView createView(Activity activity)
	{
		TiUIView view = new BranchUniversalObjectView(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;
		return view;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options)
	{
		super.handleCreationDict(options);

		// The identifier is what Branch will use to de-dupe the content across many different Universal Objects
		if (options.containsKey("canonicalIdentifier")) {
			Log.d(LCAT, "setCanonicalIdentifier");
			branchUniversalObject.setCanonicalIdentifier(options.getString("canonicalIdentifier"));
		}

		// This is where you define the open graph structure and how the object will appear on Facebook or in a deepview
		if (options.containsKey("title")) {
			Log.d(LCAT, "setTitle");
			branchUniversalObject.setTitle(options.getString("title"));
		}
		if (options.containsKey("contentDescription")) {
			Log.d(LCAT, "setContentDescription");
			branchUniversalObject.setContentDescription(options.getString("contentDescription"));
		}
		if (options.containsKey("contentImageUrl")) {
			Log.d(LCAT, "setContentImageUrl");
			branchUniversalObject.setContentImageUrl(options.getString("contentImageUrl"));
		}

		// You use this to specify whether this content can be discovered publicly - default is public
		if (options.containsKey("contentIndexingMode")) {
			Log.d(LCAT, "setContentIndexingMode");
			if (options.getString("contentIndexingMode").equals("private")) {
				Log.d(LCAT, "private");
				branchUniversalObject.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE);
			} else {
				Log.d(LCAT, "public");
				branchUniversalObject.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
			}
		}

		// Here is where you can add custom keys/values to the deep link data
		if (options.containsKey("contentMetadata")) {
			Log.d(LCAT, "addContentMetadata");
			Object contentMetadata = options.get("contentMetadata");
	        Map<String,String> hashMap = (Map<String,String>)contentMetadata;

			for(Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext();) {
			    String key = (String) iterator.next();
			    branchUniversalObject.addContentMetadata(key, hashMap.get(key));
			}
		}
	}

	//-----------  Methods ----------//
	@Kroll.method
	public void registerView()
	{
		Log.d(LCAT, "start registerView");
		branchUniversalObject.registerView();
	}

	@Kroll.method
	public void generateShortUrl(KrollDict options, KrollDict controlParams)
	{
		Log.d(LCAT, "start generateShortUrl");
		LinkProperties linkProperties = new LinkProperties();

		if (options.containsKey("feature")) {
			linkProperties.setFeature(options.getString("feature"));
		}
		if (options.containsKey("alias")) {
			linkProperties.setAlias(options.getString("alias"));
		}
		if (options.containsKey("channel")) {
			linkProperties.setChannel(options.getString("channel"));
		}
		if (options.containsKey("stage")) {
			linkProperties.setStage(options.getString("stage"));
		}
		if (options.containsKey("duration")) {
			linkProperties.setDuration(options.getInt("duration"));
		}

		if (options.containsKey("tags")) {
			ArrayList<String> tags = (ArrayList<String>) options.get("tags");
			for (String tag : tags) {
				 linkProperties.addTag(tag);
			}
		}

		if (controlParams.containsKey("$fallback_url")) {
			Log.d(LCAT, "addControlParameter $fallback_url");
			linkProperties.addControlParameter("$fallback_url", controlParams.getString("$fallback_url"));
		}
		if (controlParams.containsKey("$desktop_url")) {
			Log.d(LCAT, "addControlParameter $desktop_url");
			linkProperties.addControlParameter("$desktop_url", controlParams.getString("$desktop_url"));
		}
		if (controlParams.containsKey("$android_url")) {
			Log.d(LCAT, "addControlParameter $android_url");
			linkProperties.addControlParameter("$android_url", controlParams.getString("$android_url"));
		}
		if (controlParams.containsKey("$ios_url")) {
			Log.d(LCAT, "addControlParameter $ios_url");
			linkProperties.addControlParameter("$ios_url", controlParams.getString("$ios_url"));
		}
		if (controlParams.containsKey("$ipad_url")) {
			Log.d(LCAT, "addControlParameter $ipad_url");
			linkProperties.addControlParameter("$ipad_url", controlParams.getString("$ipad_url"));
		}
		if (controlParams.containsKey("$fire_url")) {
			Log.d(LCAT, "addControlParameter $fire_url");
			linkProperties.addControlParameter("$fire_url", controlParams.getString("$fire_url"));
		}
		if (controlParams.containsKey("$blackberry_url")) {
			Log.d(LCAT, "addControlParameter $blackberry_url");
			linkProperties.addControlParameter("$blackberry_url", controlParams.getString("$blackberry_url"));
		}
		if (controlParams.containsKey("$windows_phone_url")) {
			Log.d(LCAT, "addControlParameter $windows_phone_url");
			linkProperties.addControlParameter("$windows_phone_url", controlParams.getString("$windows_phone_url"));
		}

		final Activity activity = this.getActivity();
		branchUniversalObject.generateShortUrl(activity, linkProperties, new GenerateShortUrlListener());
	}

	@Kroll.method
	public void showShareSheet(KrollDict options, KrollDict controlParams)
	{
		Log.d(LCAT, "start showShareSheet");
		final Activity activity = this.getActivity();

		ShareSheetStyle shareSheetStyle = new ShareSheetStyle(activity, "Check this out!", "This stuff is awesome: ")
                .setCopyUrlStyle(activity.getResources().getDrawable(android.R.drawable.ic_menu_send), "Copy", "Added to clipboard")
                .setMoreOptionStyle(activity.getResources().getDrawable(android.R.drawable.ic_menu_search), "Show more")
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL);

        LinkProperties linkProperties = createLinkPropertiesDict(options, controlParams);

		branchUniversalObject.showShareSheet(activity, linkProperties, shareSheetStyle, new ShowShareSheetListener());
	}

	//-----------  Property Getter/Setter ----------//
	@Kroll.getProperty @Kroll.method
	public String getCanonicalIdentifier()
	{
		Log.d(LCAT, "getCanonicalIdentifier");
		return branchUniversalObject.getCanonicalIdentifier();
	}

	@Kroll.setProperty @Kroll.method
	public void setCanonicalIdentifier(String canonicalIdentifier)
	{
	    Log.d(LCAT, "setCanonicalIdentifier");
		branchUniversalObject.setCanonicalIdentifier(canonicalIdentifier);
	}

	@Kroll.getProperty @Kroll.method
	public String getTitle()
	{
		Log.d(LCAT, "getTitle");
		return branchUniversalObject.getTitle();
	}

	@Kroll.setProperty @Kroll.method
	public void setTitle(String title)
	{
	    Log.d(LCAT, "setTitle");
		branchUniversalObject.setTitle(title);
	}

	@Kroll.getProperty @Kroll.method
	public String getContentDescription()
	{
		Log.d(LCAT, "getContentDescription");
		return branchUniversalObject.getDescription();
	}

	@Kroll.setProperty @Kroll.method
	public void setContentDescription(String contentDescription)
	{
	    Log.d(LCAT, "setContentDescription");
		branchUniversalObject.setContentDescription(contentDescription);
	}

	@Kroll.getProperty @Kroll.method
	public String getContentImageUrl()
	{
		Log.d(LCAT, "getContentImageUrl");
		return branchUniversalObject.getImageUrl();
	}

	@Kroll.setProperty @Kroll.method
	public void setContentImageUrl(String contentImageUrl)
	{
	    Log.d(LCAT, "setContentImageUrl");
		branchUniversalObject.setContentImageUrl(contentImageUrl);
	}

	@Kroll.getProperty @Kroll.method
	public boolean isPublicallyIndexable()
	{
		Log.d(LCAT, "isPublicallyIndexable");
		return branchUniversalObject.isPublicallyIndexable();
	}

	@Kroll.setProperty @Kroll.method
	public void setContentIndexingMode(String contentIndexingMode)
	{
	    Log.d(LCAT, "setContentIndexingMode");
	    if (contentIndexingMode.equals("private")) {
			branchUniversalObject.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE);
		} else {
			branchUniversalObject.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
		}
	}

	@Kroll.setProperty @Kroll.method
	public void addContentMetadata(String key, String value)
	{
	    Log.d(LCAT, "addContentMetadata");
		branchUniversalObject.addContentMetadata(key, value);
	}

	//----------- Private Methods ----------//
	private LinkProperties createLinkPropertiesDict(KrollDict options, KrollDict controlParams)
	{
		Log.d(LCAT, "start createLinkPropertiesDict");
		LinkProperties linkProperties = new LinkProperties();

		if (options.containsKey("feature")) {
			linkProperties.setFeature(options.getString("feature"));
		}
		if (options.containsKey("alias")) {
			linkProperties.setAlias(options.getString("alias"));
		}
		if (options.containsKey("channel")) {
			linkProperties.setChannel(options.getString("channel"));
		}
		if (options.containsKey("stage")) {
			linkProperties.setStage(options.getString("stage"));
		}
		if (options.containsKey("duration")) {
			linkProperties.setDuration(options.getInt("duration"));
		}

		if (options.containsKey("tags")) {
			ArrayList<String> tags = (ArrayList<String>) options.get("tags");
			for (String tag : tags) {
				 linkProperties.addTag(tag);
			}
		}

		if (controlParams.containsKey("$fallback_url")) {
			Log.d(LCAT, "addControlParameter $fallback_url");
			linkProperties.addControlParameter("$fallback_url", controlParams.getString("$fallback_url"));
		}
		if (controlParams.containsKey("$desktop_url")) {
			Log.d(LCAT, "addControlParameter $desktop_url");
			linkProperties.addControlParameter("$desktop_url", controlParams.getString("$desktop_url"));
		}
		if (controlParams.containsKey("$android_url")) {
			Log.d(LCAT, "addControlParameter $android_url");
			linkProperties.addControlParameter("$android_url", controlParams.getString("$android_url"));
		}
		if (controlParams.containsKey("$ios_url")) {
			Log.d(LCAT, "addControlParameter $ios_url");
			linkProperties.addControlParameter("$ios_url", controlParams.getString("$ios_url"));
		}
		if (controlParams.containsKey("$ipad_url")) {
			Log.d(LCAT, "addControlParameter $ipad_url");
			linkProperties.addControlParameter("$ipad_url", controlParams.getString("$ipad_url"));
		}
		if (controlParams.containsKey("$fire_url")) {
			Log.d(LCAT, "addControlParameter $fire_url");
			linkProperties.addControlParameter("$fire_url", controlParams.getString("$fire_url"));
		}
		if (controlParams.containsKey("$blackberry_url")) {
			Log.d(LCAT, "addControlParameter $blackberry_url");
			linkProperties.addControlParameter("$blackberry_url", controlParams.getString("$blackberry_url"));
		}
		if (controlParams.containsKey("$windows_phone_url")) {
			Log.d(LCAT, "addControlParameter $windows_phone_url");
			linkProperties.addControlParameter("$windows_phone_url", controlParams.getString("$windows_phone_url"));
		}

		return linkProperties;
	}

	//----------- Inner Classes: Listeners ----------//
    protected class GenerateShortUrlListener implements Branch.BranchLinkCreateListener
    {
    	@Override
	    public void onLinkCreate(String url, BranchError error) {
	    	Log.d(LCAT, "inside onLinkCreate");
	    	BranchUniversalObjectProxy self = BranchUniversalObjectProxy.this;
	        if (error == null) {
	            Log.d(LCAT, "link to share: " + url);
	            self.fireEvent("bio:generateShortUrl", url);
	        } else {
	        	Log.d(LCAT, error.getMessage());
	        	self.fireEvent("bio:generateShortUrl", error.getMessage());
	        }
	    }
    }

    protected class ShowShareSheetListener implements Branch.BranchLinkShareListener
    {
    	@Override
	    public void onShareLinkDialogLaunched() {
	    	Log.d(LCAT, "inside onShareLinkDialogLaunched");
	    	BranchUniversalObjectProxy self = BranchUniversalObjectProxy.this;
	    	self.fireEvent("bio:shareLinkDialogLaunched", null);
	    }

	    @Override
	    public void onShareLinkDialogDismissed() {
	    	Log.d(LCAT, "inside onShareLinkDialogDismissed");
	    	BranchUniversalObjectProxy self = BranchUniversalObjectProxy.this;
	    	self.fireEvent("bio:shareLinkDialogDismissed", null);
	    }

	    @Override
	    public void onLinkShareResponse(String sharedLink, String sharedChannel, BranchError error) {
	    	Log.d(LCAT, "inside onLinkCreate");
	    	BranchUniversalObjectProxy self = BranchUniversalObjectProxy.this;
	    	KrollDict response = new KrollDict();
	    	if (error == null) {
	    		response.put("sharedLink", sharedLink);
	    		response.put("sharedChannel", sharedChannel);
	            Log.d(LCAT, "sharedLink: " + sharedLink);
	            Log.d(LCAT, "sharedChannel: " + sharedChannel);
	        } else {
	        	Log.d(LCAT, error.getMessage());
	        	response.put("error", error.getMessage());
	        }
	        self.fireEvent("bio:shareLinkResponse", response);
	    }

	    @Override
	    public void onChannelSelected(String channelName) {
	    	Log.d(LCAT, "inside onChannelSelected");
	    	Log.d(LCAT, "channelName: " + channelName);
	    	BranchUniversalObjectProxy self = BranchUniversalObjectProxy.this;
	    	self.fireEvent("bio:shareChannelSelected", channelName);
	    }
	}
}
