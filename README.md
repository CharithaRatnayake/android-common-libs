# Android Common Libs (v1.0.0) #

## What is this repository for? ##

This repository is help for common featurs and all common dependencis that up-to date.

* IDE: Android Studio 3.5.1
* App Name: Android Common Libs

List of common functions,

    1 Custom Widgets (CustomEditText, CustomTextView, CustomImageView, CustomButton, CircleImageView)
    2 Base Activity/ Fragment
    3 Dialogs (ProgressDialog)
    4 Validator class for EditText
    5 Common Utils (AppTimeout, ContextManager, DateTimeUtil, FileManager, Helper, TinyDb)

## How do I get set up? ##
    
### Configuration ###

Clone project

``https://github.com/CharithaRatnayake/android-common-libs.git``
           
### Dependencies ###

```

def retrofitVersion = '2.7.1'
def androidSupport = "1.1.0"
def daggerVersion = '2.25.4'
def rxJavaVersion = '2.0.2'
def butterKnifeVersion = '10.2.1'
def reactivestreams_version = "1.1.1"
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    implementation "androidx.appcompat:appcompat:$androidSupport"
    implementation "com.google.android.material:material:$androidSupport"
    implementation "androidx.recyclerview:recyclerview:$androidSupport"
    implementation "androidx.cardview:cardview:1.0.0"

    implementation "com.google.dagger:dagger:$daggerVersion"
    annotationProcessor "com.google.dagger:dagger-compiler:$daggerVersion"
    implementation "com.google.dagger:dagger-android-support:$daggerVersion"
    annotationProcessor "com.google.dagger:dagger-android-processor:$daggerVersion"

    implementation "io.reactivex.rxjava2:rxandroid:$rxJavaVersion"
    implementation "io.reactivex.rxjava2:rxjava:$rxJavaVersion"

    // Reactive Streams (convert Observable to LiveData)
    implementation "android.arch.lifecycle:reactivestreams:$reactivestreams_version"

    implementation "com.jakewharton:butterknife:$butterKnifeVersion"
    annotationProcessor "com.jakewharton:butterknife-compiler:$butterKnifeVersion"

    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'

    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.wang.avi:library:2.1.3'
    implementation 'id.zelory:compressor:2.1.0'
}

```

**Version**

| Version Code | Version Name | Release note |
| --- | --- | --- |
|1| ```1.0.0```  | Main common features added. |

## Custom Widgets (CustomEditText, CustomTextView, CustomImageView, CustomButton, CircleImageView) ##

Common widgets that need to add your projects.

### CustomEditText ###

CustomEditText extend by android AppCompatEditText

| # | Function | Description |
| --- | --- | --- |
| 1 |```setAnyText```| Set text null no exception |
| 2 |```setCustomFont```| Set custom font by '''app:fontType''' |
| 3 |```getTextString```| Get text string value return empty text if null |
| 4 |```getTextInt```| get the int value of the custom edit text |
| 5 |```getTextDouble```| get the double value of the custom edit text |
| 6 |```setDateTime```| Set date and time '''DateTimeUtil.DEFAULT_DATE_FORMAT''' format |
| 7 |```setCurrency```| Set the text as a currency in edit text |
| 8 |```getTrimText```| Trim text which removed extra space |
| 9 |```setEditability```| Set editable false if you want to deactivate the edittext |

### CustomTextView ###

CustomTextView extend by android AppCompatTextView

| # | Function | Description |
| --- | --- | --- |
| 1 |```setAnyText```| Set text null no exception |
| 2 |```setCustomFont```| Set custom font by '''app:fontType''' |
| 3 |```getTextString```| Get text string value return empty text if null |
| 4 |```getTextInt```| get the int value of the custom edit text |
| 5 |```getTextDouble```| get the double value of the custom edit text |
| 6 |```setDateTime```| Set date and time '''DateTimeUtil.DEFAULT_DATE_FORMAT''' format |
| 7 |```setCurrency```| Set the text as a currency in edit text |
| 8 |```getTrimText```| Trim text which removed extra space |

### CustomButton ###

CustomButton extend by android AppCompatButton

| # | Function | Description |
| --- | --- | --- |
| 1 |```setAnyText```| Set text null no exception |
| 2 |```setCustomFont```| Set custom font by '''app:fontType''' |

### CustomImageView ###

CustomImageView extend by android AppCompatImageView

| # | Function | Description |
| --- | --- | --- |
| 1 |```loadImage```| Load image from piccaso lib |

### CircleImageView ###

CircleImageView extend by android CustomImageView

Link: https://github.com/hdodenhof/CircleImageView

## Base Activity/ Fragment ##

### BaseActivity ###

BaseActivity extend by DaggerAppCompatActivity

| # | Function | Description |
| --- | --- | --- |
| 1 |```activityToActivity```| Start activity from class name |
| 2 |```showWaiting```| Show fullscreen progress dialog and disable humen interaction to other views |
| 3 |```dismissWaiting```| Dissmiss above dialog |
| 4 |```startFragment```| Start fragment and save in local emory as a map |
| 5 |```getFragment```| Get current top fragment |
| 6 |```showMessage```| Show general massages in toast text |

### BaseFragment ###

BaseFragment extend by DaggerFragment

| # | Function | Description |
| --- | --- | --- |
| 1 |```showWaiting```| Show fullscreen progress dialog and disable humen interaction to other views |
| 2 |```dismissWaiting```| Dissmiss above dialog |
| 3 |```showMessage```| Show general massages in toast text |
