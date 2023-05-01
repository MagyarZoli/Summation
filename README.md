# Summation

## Description
Adds the values as selected.

## Features
Summation class:
with its static methods, the Number class can calculate inheritance classes, return the sum of its values, transform.
can handle:
`Array`
`Collection`
`Map`
It can decompose these types and also calculate the internal values.
Class cannot be inherited or instantiated, its methods cannot be overridden.

## Example
> _**summation(x)**_<br>
> Calculates the sum of the values of the corresponding variable specified.
> It can handle classes inherited from the `Number` class.
> Preferred `Number, Collection, Map`. Can accept classes and interfaces derived from `Number[]`, `Collection`, or classes and interfaces derived from `Map`.<br>
> *x* `Number, Collection, Map` type variable that calculates the sum of its internal values, internal values must be of type Number!<br>
> *return* After deciding on the type, it gives the sum of the values in it.

> _**summationArray(x)**_<br>
> It calculates the sum of the values of the given corresponding variable, and returns it stored in an `array`.
> It can handle classes inherited from the `Number` class.
> Preferred `Number, Collection, Map`. Can accept classes and interfaces derived from `Number[]`, `Collection`, or classes and interfaces derived from `Map`.<br>
> *x* `Number, Collection, Map` type variable that calculates the sum of its internal values.<br>
> *return* An `array` containing the internal values of the expanded selected variable. Its type will be the type of the variable's internal values.

> _**summationCollection(x, classCollectionNameDotClass)**_<br>
> Calculates the sum of the values of the given variable, then `Collection` returns it as a specified derived class.
> It can handle classes inherited from the Number class.
> Collection Specified Collection type
> Preferred `Number, Collection, Map`. Can accept classes and interfaces derived from `Number[]`, `Collection`, or classes and interfaces derived from `Map`.<br>
> *x* `Number, Collection, Map` type variable that calculates the sum of its internal values.<br>
> *classCollectionNameDotClass* Must be specified to define the derived type of Collection.<br>
> *return* Returns values as specified `Collection` type.

> _**summationMap(x, classMapNameDotClass)**_<br>
> Calculates the sum of the values of the given variable and returns it stored as the specified `Map` class.
> preferred `Number, Collection, Map`. Given key can be of `Number, Collection, Map` type. 
> It can handle classes inherited from the `Number` class.
> Specific `Map` type.
> preferred `Number, Collection, Map`. Can accept classes and interfaces derived from `Number[]`, `Collection`, or classes and interfaces derived from `Map`.<br>
> *x* `Number, Collection or Map` type variable that calculates the sum of its internal values.
> *classMapNameDotClass* Required to specify the `Map` derived type.
> *return* `Map` is one of the specified derived classes and returns two values, the key of `Map` is filled in the specified way.
> If there is no or the key type or size is not suitable, then the default is to upload.

### Authors
Magyar Zoltán

### Contact
magyarz95@gmail.com