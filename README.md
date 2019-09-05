# ProgressBar

[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://github.com/liihuu/ProgressBar)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](./LICENSE)

Beautiful progress bar for android.

## Usage
### gradle
```groovy
implementation 'com.geqian.progressbar:progressbar:1.0.0'
```

### xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:layout_height="match_parent">
    <com.geqian.progressbar.ProgressBar
        android:id="@+id/progressBar"
        android:layout_marginTop="100dp"
        android:layout_width="match_parent"
        android:layout_height="10dp"
        app:backgroundColor="#d8d8d8"
        app:startFillColor="#505050"
        app:middleFillColor="#B03060"
        app:endFillColor="#e03060"
        app:arrowPointRadius="6dp"/>

    <com.geqian.progressbar.FloatTextProgressBar
       android:id="@+id/floatTextProgressBar"
       android:layout_width="match_parent"
       android:layout_marginTop="50dp"
       android:layout_height="30dp"
       app:backgroundColor="#d8d8d8"
       app:fillColor="#ff0000"
       app:triangleColor="#ff0000"
       app:rectColor="#ff0000"
       app:textColor="#fff"/>

    <com.geqian.progressbar.CircleProgressBar
        android:id="@+id/circleProgressBar"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="50dp"
        app:backgroundColor="#d8d8d8"
        app:fillColor="#ff0000"
        app:progressWidth="5dp"
        app:textColor="#505050"/>
</LinearLayout>
```
### activity
```java
ProgressBar progressBar = findViewById(R.id.progressBar);
progressBar.setProgress(50);
    
FloatTextProgressBar floatTextProgressBar = findViewById(R.id.floatTextProgressBar);
floatTextProgressBar.setProgress(50);
    
CircleProgressBar circleProgressBar = findViewById(R.id.circleProgressBar);
circleProgressBar.setProgress(50);
```

## Screenshots
![screenshot](./screenshots/screenshot.png)

## License
Copyright (c) 2017 lihu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
    
    
