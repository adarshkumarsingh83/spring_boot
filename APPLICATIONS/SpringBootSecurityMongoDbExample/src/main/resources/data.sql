>db.version();
>show dbs;
>use <dbname>
>show collections;
>db.help();
>db.<collection>.help();

> db.user.insert({  "_class" : "com.espark.adarsh.domain.User", "email" : "adarsh.radha@espark.com", "password" : "$2a$10$ZIo6rIwiEmzTFA/qKAg93eo2/xgW6TO6XN2inAf4Qz1tGXmtz7mhC" ,"role":"ADMIN"});
> db.user.insert({  "_class" : "com.espark.adarsh.domain.User", "email" : "radha.adarsh@espark.com", "password" : "$2a$10$RRJP9vFPRJro7WITIeXy9OZ9cjURoCKQBBdDhMamtwuGhzg6WOD8G" ,"role":"ADMIN"});
> db.user.find().pretty();
{
        "_id" : 1,
        "_class" : "com.espark.adarsh.domain.User",
        "email" : "adarsh.radha@espark.com",
        "password" : "$2a$10$ZIo6rIwiEmzTFA/qKAg93eo2/xgW6TO6XN2inAf4Qz1tGXmtz7mhC",
        "role" : "ADMIN"
}
{
        "_id" : 2,
        "_class" : "com.espark.adarsh.domain.User",
        "email" : "radha.adarsh@espark.com",
        "password" : "$2a$10$RRJP9vFPRJro7WITIeXy9OZ9cjURoCKQBBdDhMamtwuGhzg6WOD8G",
        "role" : "ADMIN"
}

>db.user.remove({'key':'value'});