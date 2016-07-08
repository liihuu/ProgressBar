Screenshots
====
![aa](https://github.com/geqiancrazy/geqiancrazy/blob/master/ProgressBar/Screenshots/screenshot.png)

Usage
===================================
xml
-----
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
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
            app:startFillColor="#ff0000"
            app:triangleColor="#ff0000"
            app:rectColor="#ff0000"
            app:textColor="#fff"/>
    </LinearLayout>
activity
-----
    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setProgress(50);
    
    FloatTextProgressBar floatTextProgressBar = (FloatTextProgressBar) findViewById(R.id.floatTextProgressBar);
    floatTextProgressBar.setProgress(30);
custom attribute
-----
    <declare-styleable name="progressBar">
        <attr name="progress" format="integer" />
        <attr name="backgroundColor" format="reference|color" />
        <attr name="startFillColor" format="reference|color" />
        <attr name="middleFillColor" format="reference|color" />
        <attr name="endFillColor" format="reference|color" />
        <attr name="arrowPointColor" format="reference|color" />
        <attr name="arrowPointRadius" format="dimension|reference" />
        <attr name="triangleColor" format="reference|color" />
        <attr name="rectColor" format="reference|color" />
        <attr name="textColor" format="reference|color" />
    </declare-styleable>
