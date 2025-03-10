# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

**1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.**  
   `==` compares references (memory addresses) of two objects while`.equals()`compares the content equality of two strings.[^1]
   ```java
    String str1 = new String("Hello");
    String str2 = new String("Hello");
    str1!=str2;
    str1.equals(str2);
   ```
**2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner?**  
`sort` method from `Collections` can be used.[^2] For example,
```java
List<String> fruits = Arrays.asList("apple", "Banana", "Orange", "grape");
Collections.sort(fruits, String.CASE_INSENSITIVE_ORDER);
```
**3. In our version of the solution, we had the following code (snippet)**  
 ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
 ```
 **Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point.**   
For example, as `>=` also contains `>`, if`>` goes first,`>=` would be recognized as `>`. But putting `>=` ahead would avoid this.


**4. What is the difference between a List and a Set in Java? When would you use one over the other?**  
List is ordered, indexed and allows duplicates. So when order, index and duplicate elements are necessary or important, list should be used.  
Set is unordered without duplicates and index. So when duplicate is not allowed and order and index does not matter, set can be used.[^3]

**5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here?**   
A `Map` in Java is a collection that stores key-value pairs, where each key is unique, and it maps to a specific value. Using a `Map` can be helpful in scenarios where we need to associate a column identifier (the key) with a specific column of data (the value).[^4]




**6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?**  
Enum is a data type consisting a predefined set of named values.  
In this application, using enum can eliminate the risk of typos or inconsistencies when referring to column names throughout the application. Some enum constants are associated with additional properties and methods, keeping related logic and data together and makes the code more maintainable.[^5]

**7. Rewrite the following as an if else statement inside the empty code block.**   
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
      ```

    if (ct == CMD_QUESTION || ct == CMD_HELP) {
        processHelp();
      } else if (ct == INVALID) {
           CONSOLE.printf("%s%n", ConsoleText.INVALID);
      } else {
           CONSOLE.printf("%s%n", ConsoleText.INVALID);
      }


## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here
*******Welcome to the BoardGame Arena Planner.*******
*******欢迎使用桌游竞技场规划助手！*******

A tool to help people plan which games they 
want to play on Board Game Arena. 
一款帮助人们规划玩哪些桌游的工具。

To get started, enter your first command below, or type ? or help for command options.
请在下面输入您的第一个命令，或者输入 ‘?’ 或 ‘帮助’ 获取命令选项。

```

**Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?**  

Localization can help adapting a product to meet the linguistic, cultural, and functional expectations of users in different regions as there are huge differences between various languages and cultures.   
At present, there are approximately 7,000 languages spoken globally[^6]. The regions with internet access globally are primarily covered by major languages such as English, Chinese, Spanish, French, and Arabic. 
As internet penetration increases, by 2024, approximately 5.5 billion people will be using the internet, accounting for 68% of the global population[^7]. So to tap a product into broader markets, localization is necessary.  
Dangers of trying to localize a program:  
1, cultural misunderstandings: happens when certain word is culturally sensitive in some area， such as number four is avoided by Chinese.  
2, incorrect translation happens when a word has different meanings, like “lick fingers chicken” may have a few translations.[^8]   
3, legal violations, some translations may violate local regulations, like some kinds of alcohol are forbidden somewhere.  

To reduce ‘hick ups’, we can use professional translators and local experts to ensure precision and avoid misunderstandings. 
Also, companies can hire people to conduct cultural sensitivity check and trials before launching[^9]. 
It is also important to keep tracks with the market response and customers feedbacks, enabling to adjust localization strategies as soon as possible.[^10]


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 

## References
[^1]: Difference Between == Operator and equals() Method in Java. https://www.geeksforgeeks.org/difference-between-and-equals-method-in-java/ Accessed: 2025-03-08.  
[^2]: Java Program to sort a List in case insensitive order. https://www.tutorialspoint.com/java-program-to-sort-a-list-in-case-insensitive-order Accessed: 2025-03-08.  
[^3]: Difference Between List and Set in Java. https://www.geeksforgeeks.org/difference-between-list-and-set-in-java/ Accessed: 2025-03-08.  
[^4]: Map Interface in Java. https://www.geeksforgeeks.org/map-interface-java-examples/ Accessed: 2025-03-08.  
[^5]: Enum Types. https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html Accessed: 2025-03-08.    
[^6]: Languages of the World.https://www.ethnologue.com/?utm_source=chatgpt.com Accessed: 2025-03-08.  
[^7]: Statistics.https://www.itu.int/en/ITU-D/Statistics/pages/stat/default.aspx?utm_source=chatgpt.com Accessed: 2025-03-08.  
[^8]: The Hilarious Translation Mistake KFC China Made With Its Slogan. https://www.mashed.com/747566/the-hilarious-translation-mistake-kfc-china-made-with-its-slogan/ Accessed: 2025-03-08.  
[^9]: Multimedia language services. https://toppandigital.com/service/multimedia-language-services/ Accessed: 2025-03-08.  
[^10]: Global App Testing. https://www.globalapptesting.com Accessed: 2025-03-08.  