![](https://github.com/T45K/SCM/workflows/Build/badge.svg)
[![codecov](https://codecov.io/gh/T45K/SCM/branch/master/graph/badge.svg)](https://codecov.io/gh/T45K/SCM)

# SCM 
SCM is a clone (duplicated code) detection tool for Java.
Based on the concept of [Code Clone Matching](https://arxiv.org/pdf/2003.05615.pdf), SCM detects code fragments similar to query using a token-based technique.
SCM can detect type-1 (exact copy) and type-2 (renamed) clones.

**Tips**<br>
SCM is developed for my learning of **String Search Algorithm**. 
I can't guarantee SCM will work properly.
Currently, SCM supports the following algorithm.
- Rolling Hash

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

## To be implemented
SCM will support the following algorithms.
- Suffix Array with Naive Array Construction
- Suffix Array with Doubling
- Suffix Array with SA-IS
- Longest Common Prefix Array

## Algorithms Description

In this description, the input file consists of **n** tokens, and the query consists of **m** tokens.

### Rolling Hash
**Rolling Hash** is a hash function where the input is hashed in a window that moves through the input(by [Wikipedia](https://en.wikipedia.org/wiki/Rolling_hash)).
Its computational complexity is *O(n + m)*.
In SCM, the base number is `1,020,544,910`, the modulo number is `2,147,483,647`, and calculations are made on a range of 0 to 2^31.

## License
MIT License
