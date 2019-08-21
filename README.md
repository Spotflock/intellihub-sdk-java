# Intellihub SDK (Java)

[![](https://intellihub.ai/static/img/logo-high.png)](https://intellihub.ai)
Intellihub renders a comprehensive spectrum of solutions that can be accessed by users on-demand from our pool of transformational technologies.

### Installation
Intellihub SDK requires Java 1.8 + . Go to https://intellihub.ai and create an app. On creation of an app, you will get an API Key.

```sh
import com.spotflock.intellihub.IntellihubClient;

IntellihubClient c = new IntellihubClient("xxx");
String response = c.sentimentAnalysis("I am feeling sick.");
System.out.print(response);
```

For more details, visit https://intellihub.ai
