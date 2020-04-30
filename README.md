![](https://github.com/T45K/SCM/workflows/Build/badge.svg)
[![codecov](https://codecov.io/gh/T45K/SCM/branch/master/graph/badge.svg)](https://codecov.io/gh/T45K/SCM)

# SCM 
SCM is a clone (duplicated code) detection tool for Java.
Based on the concept of [Code Clone Matching](https://arxiv.org/pdf/2003.05615.pdf), SCM detects code fragments similar to query by using a token-based technique.
SCM can detect type-1 (exact) and type-2 (renamed) clones.

**Tips**<br>
SCM is developed for my learning of **String Search Algorithm**. 
I can't guarantee SCM will work properly.
Currently, SCM supports the following algorithm.
- Rolling Hash
- Suffix Array with naive construction
- Suffix Array with Doubling construction

## Requirements
JDK 14

## Usage
1. Download the latest version of SCM from [here](https://github.com/T45K/SCM/releases).
2. `java -jar SimpleCloneMatcher.jar -i ... -q ...`

## Options
|Name|Option|Alias|Description|
|:-:|:-:|:-:|:-|
|Directory|`-i`|`--input-dir`|Path of directory containing all source files from which you want to detect clones.<br>You must specify this or `-s` option.|
|Source file|`-s`|`--source-file`|Path of source file from which you want to detect clones.<br>You must specify this or `-i` option|
|Query|`-q`|`--query`|Query. You must specify this option.|
|Algorithm|`-a`|`--algorithm`|Algorithm. You can specify one from the following algorithms.<br>`ROLLING_HASH(default)`,`SUFFIX_ARRAY_NAIVE`,`SUFFIX_ARRAY(_DOUBLING)`|

## To be implemented
SCM will support the following algorithms.
- Suffix Array with SA-IS
- Longest Common Prefix Array

## Algorithms Description

In this description, the input file consists of **n** tokens, and the query consists of **m** tokens.

### Rolling Hash
**Rolling Hash** is a hash function where the input is hashed in a window that moves through the input(by [Wikipedia](https://en.wikipedia.org/wiki/Rolling_hash)).
Its computational complexity is *O(n + m)*.
In SCM, the base number is `1,020,544,910`, the modulo number is `2,147,483,647`, and calculations are made on a range of 0 to 2^31.

### Suffix Array
**Suffix Array** is a sorted array of all suffixes of a string(by [Wikipedia](https://en.wikipedia.org/wiki/Suffix_array)).
By constructing Suffix Array, you can use binary search so that you can search strings in *O(m log(n))*.

SCM implements the following methods.
- Naive

Naive method is just sorting all suffixes.
Comparing strings whose length are N requires *O(n)*, thus, Naive method requires *O(n^2 log(n))*.

- Doubling

Doubling method is sorting and comparing suffixes by length of power of 2.
It requires *O(n (log n) ^ 2)*.

## License
MIT License
