 ### Cron Parser
 
Parse a cron expression and return the times at which cron fires  
 
 
 ### Input values
 
|Field name|Allowed values|Allowed special characters
|:----|:----|:----|
|Seconds | 0-59 | * / , -
|Minutes | 0-59 | * / , -
|Hours | 0-23 | * / , -
|Day of month | 1-31 | * / , -
|Month | 1-12 | * / , -
|Day of week | 0-6 | * / , -

### Installation

You need sbt and java to run this project

To build execute

```$xslt
sbt "assembly"
```

To run

```
java -jar target/scala-2.13/CronParser-assembly-0.1.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Example

```$xslt
java -jar target/scala-2.13/CronParser-assembly-0.1.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```


### Limitations

* no validation
* no support for days/months string representation
