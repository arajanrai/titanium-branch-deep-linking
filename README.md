# Branch Titanium SDK

## Full documentation
Exhaustive documentation can be found on our [documentation portal](https://dev.branch.io).  you may also find our [support portal and user forums](http://support.branch.io) helpful.

The Titanium SDK shares the same code base as the iOS and Android SDKs, and enables you to call all the same functions from your Titanium app.

_____



## Installation

To use the module, go to the `android/iphone` folder's respective `dist` folders.
Inside, you should be able to find the compressed module. Extract the contents to your
titanium `modules` folder






## API Reference

1. Branch Session
  + [.initSession()](#initsession)
  + [.getAutoSession()](#getAutoSession)
  + [.getLatestReferringParams()](#getlatestreferringparams)
  + [.getFirstReferringParams()](#getFirstReferringParams)
  + [.setIdentity(identity)](#setIdentity-identity)
  + [.logout()](#logout)
  + [.userCompletedAction()](#userCompletedAction)

2. Branch Universal Object
  + [.createBranchUniversalObject(options)](#createBranchUniversalObject-options)
  + [.registerView()](#registerView)
  + [.generateShortUrl(options, controlParameters)](#generateShortUrl-linkProperties-controlParameters)
  + [.showShareSheet(options, controlParameters)](#showShareSheet-linkProperties-controlParameters)

3. Referral System Rewarding
  + [.loadRewards()](#loadRewards)
  + [.redeemRewards(value)](#redeemRewards-value)
  + [.creditHistory()](#creditHistor)

___

* * *

### setDebug()

Setting the SDK debug flag will generate a new device ID each time the app is installed
instead of possibly using the same device id.  This is useful when testing.

This needs to be set before the Branch.init call!!!

___



### initSession()

Initializes the branch instance.

##### Usage
```js
branch.initSession();
```

##### Callback
To implement the callback, you must add a listener to the event `bio:initSession`.
The event returns a dictionary object where you can read the link the user was referred by and other related data.

Branch returns explicit parameters every time. Here is a list, and a description of what each represents.

* `~` denotes analytics
* `+` denotes information added by Branch
* (for the curious, `$` denotes reserved keywords used for controlling how the Branch service behaves)

| **Parameter** | **Meaning**
| --- | ---
| ~channel | The channel on which the link was shared, specified at link creation time
| ~feature | The feature, such as `invite` or `share`, specified at link creation time
| ~tags | Any tags, specified at link creation time
| ~campaign | The campaign the link is associated with, specified at link creation time
| ~stage | The stage, specified at link creation time
| ~creation_source | Where the link was created ('API', 'Dashboard', 'SDK', 'iOS SDK', 'Android SDK', or 'Web SDK')
| +match_guaranteed | True or false as to whether the match was made with 100% accuracy
| +referrer | The referrer for the link click, if a link was clicked
| +phone_number | The phone number of the user, if the user texted himself/herself the app
| +is_first_session | Denotes whether this is the first session (install) or any other session (open)
| +clicked_branch_link | Denotes whether or not the user clicked a Branch link that triggered this session
| +click_timestamp | Epoch timestamp of when the click occurred


**Note:** `Branch.initSession()` must be called prior to calling any other Branch functions.

___



### getAutoSession()

Initializes the branch instance with automatic session management. Implements same callback as of `initSession`.

##### Usage
```js
branch.getAutoSession();
```

##### Callback
Same as `initSession`.

**Note:** `Branch.getAutoSession()` must be called prior to calling any other Branch functions. `Branch.getAutoSession()` in iOS is equivalent to `Branch.initSession()` which already has automatic session management. Use `getAutoSession()` to avoid having separate calls for Android and iOS.

___



### getLatestReferringParams()

Retrieves the session (install or open) parameters.

##### Usage
```js
var sessionParams = branch.getLatestReferringParams();
```

___



### getFirstReferringParams()

Retrieves the install session parameters.

##### Usage
```js
var installParams = branch.getFirstReferringParams();
```

___



### setIdentity(identity)

Sets the identity of a user and returns the data. To use this function, pass
a unique string that identifies the user - this could be an email address,
UUID, Facebook ID, etc.

**Parameters**

**identity**: `string`, _required_ - a string uniquely identifying the user – often a user ID or email address.

##### Usage
```js
branch.setIdentity(identity);
```

___



### logout()

Logs out the current session, replaces session IDs and identity IDs.

##### Usage
```js
branch.logout();
```

___



### userCompletedAction(customAction)

Registers custom events.

**Parameters**

**customAction**: `string`, _required_ - a string for your custom action (e.g. "complete_purchase", "wrote_message", "finished_level_ten", etc).

##### Usage
```js
branch.userCompletedAction(customAction);
```

___



## Branch Universal Object



### createBranchUniversalObject(options)

Creates a new Branch Universal Object.

**Parameters**

**options**: `dictionary`, _required_ - options in creating the object.

| **Key** | Type | **Meaning**
| --- | --- |---
| canonicalIdentifier | `string` | The identifier of the object
| title | `string` | The title of the object
| contentDescription | `string` | The short description of the object
| contentImageUrl | `string` | URL of the image used by the object
| contentIndexingMode | `string` | Indexing mode of the object. Set as "private" or "public".
| contentMetadata | `dictionary` | Custom keys and values as metadata of the object


##### Usage
```js
branchUniversalObjectProxy.createBranchUniversalObject({
  "canonicalIdentifier" : "sample-id",
  "title" : "Sample",
  "contentDescription" : "This is a sample",
  "contentImageUrl" : "http://sample-host.com/media/1235904.jpg",
  "contentIndexingMode" : "private",
  "contentMetadata" : {
      "key" : "value",
      "key2" : "value2"
  },
});
```

___



### registerView()

Registers the view.

##### Usage
```js
branchUniversalObjectProxy.registerView();
```

___



### generateShortUrl(options, controlParameters)

Generates a URL for deep linking.

**Parameters**

**options**: `dictionary`, _required_ - options needed to generate the URL.

| **Key** | Type | **Meaning**
| --- | --- |---
| feature | `string` | The feature of the link
| alias | `string` | The alias of the link
| channel | `string` | The channel of the link
| stage | `string` | The stage of the link
| duration | `int` | duration of the link.

**controlParameters**: `dictionary`, _required_ - link properties needed to generate the URL.

| **Key** | Type | **Meaning**
| --- | --- |---
| $fallback_url | `string` | The fallback URL
| $desktop_url | `string` | The URL for desktop
| $android_url | `string` | The URL for Android
| $ios_url | `string` | The URL for iPhone
| $ipad_url | `string` | The URL for iPad
| $fire_url | `string` | The URL for Kindle Fire
| $blackberry_url | `string` | The URL for Blackberry
| $windows_phone_url | `string` | The URL for Windows phone

##### Usage
```js
branchUniversalObjectProxy.generateShortUrl({
  "feature" : "sample-feature",
  "alias" : "sample-alias",
  "channel" : "sample-channel",
  "stage" : "sample-stage",
  "duration" : 1,
}, {
  "$desktop_url" : "http://desktop-url.com",
});
```

##### Callback
To implement the callback, you must add a listener to the event `bio:generateShortUrl`.
The event returns a string object containing the generated link.

**Note:** Avoid passing `alias` in iOS. Adding an `alias` key in the `options` parameter will return a Non-Universal link which will not work in iOS 9.2.

___



### showShareSheet(options, controlParameters)

Shows the custom share sheet to share the link.

**Parameters**

**options**: `dictionary`, _required_ - options for the share sheet.

| **Key** | Type | **Meaning**
| --- | --- |---
| feature | `string` | The feature of the link
| alias | `string` | The alias of the link
| channel | `string` | The channel of the link
| stage | `string` | The stage of the link
| duration | `int` | duration of the link.

**controlParameters**: `dictionary`, _required_ - link properties needed to generate the URL.

| **Key** | Type | **Meaning**
| --- | --- |---
| $fallback_url | `string` | The fallback URL
| $desktop_url | `string` | The URL for desktop
| $android_url | `string` | The URL for Android
| $ios_url | `string` | The URL for iPhone
| $ipad_url | `string` | The URL for iPad
| $fire_url | `string` | The URL for Kindle Fire
| $blackberry_url | `string` | The URL for Blackberry
| $windows_phone_url | `string` | The URL for Windows phone

##### Usage
```js
branchUniversalObjectProxy.showShareSheet({
  "feature" : "sample-feature",
  "alias" : "sample-alias",
  "channel" : "sample-channel",
  "stage" : "sample-stage",
  "duration" : 1,
}, {
  "$desktop_url" : "http://desktop-url.com",
});
```

##### Callback
To implement the callback, you must add listeners to the following events:

`bio:shareLinkDialogLaunched`
- The event fires when the share sheet is presented.

`bio:shareLinkDialogDismissed`
- The event fires when the share sheet is dismissed.

`bio:shareLinkResponse`
- The event returns a dictionary of the response data.

`bio:shareChannelSelected`
- The event fires a channel is selected.

**Note:** Callbacks in iOS are ignored. There is no need to implement them as the events are handled by `UIActivityViewController`.

**Note:** Avoid passing `alias` in iOS. Adding an `alias` key in the `options` parameter will return a Non-Universal link which will not work in iOS 9.2.

___



## Referral System Rewarding



### loadRewards()

Retrieves the Reward Balance.

##### Usage
```js
branch.loadRewards();
```

##### Callback
To implement the callback, you must add a listener to the event `bio:loadRewards`.
The event returns a dictionary object containing the balance.

___


### redeemRewards(value)

Redeems a reward with the given amount/value.

**Parameters**

**value**: `int`, _required_ - amount to be redeemed.

##### Usage
```js
branch.redeemRewards(value);
```

___


### creditHistory()

Retrieves the credit history.

##### Usage
```js
branch.creditHistory();
```

##### Callback
To implement the callback, you must add a listener to the event `bio:creditHistory`.
The event returns a dictionary object containing the credit history.

* * *



## Bugs / Help / Support

Feel free to report any bugs you might encounter in the repo's issues. Any support inquiries outside of bugs
please send to [support@branch.io](mailto:support@branch.io).
