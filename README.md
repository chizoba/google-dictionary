Google Dictionary
========

Want to easily check the meaning of any word, phrase or slang in your app? Just import this ;) 

![alt tag](https://github.com/chizoba/google-dictionary/blob/master/google-dictionary-preview.gif)

## Setting up

### Dependency

*  Maven
```groovy
<dependency>
  <groupId>com.github.chizoba</groupId>
  <artifactId>google-dictionary</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

*  Gradle
```groovy
implementation 'com.github.chizoba:google-dictionary:1.0.0'
```

## Usage

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Declare a TextView you want to implement the "google search function" to 
    TextView tv = (TextView) findViewById(R.id.textView);

    // Enable TextView to be able to select text on long click
    tv.setTextIsSelectable(); // or do this via xml

    // Create an instance of the GoogleDictionary class
    GoogleDictionary gd = new GoogleDictionary();

    // Create a FragmentManager 
    FragmentManager fm = getSupportFragmentManager();

    // Call the setDictionaryEnabled() method passing in your TextView and Fragment Manager
    gd.setDictionaryEnabled(tv, fm);
}    
```

## License

    Copyright 2017 Chizoba Ogbonna

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
