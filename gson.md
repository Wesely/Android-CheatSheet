# GSON

## From JSON

example input : `[{“name”:”name0”,”age”:0}]`

### Read as an Object

```kt
Person person = gson.fromJson(str, Person.class);
```

## To JSON / To String

```kt
Gson().toJson(it)
```

### Read as Collection

```kt
List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
```
