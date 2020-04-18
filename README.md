![](https://github.com/T45K/SCM/workflows/Build/badge.svg)
[![codecov](https://codecov.io/gh/T45K/SCM/branch/master/graph/badge.svg)](https://codecov.io/gh/T45K/SCM)

# SCM 
SCM is a clone (duplicated code) ditection tool for Java.
Based on the concept of [Code Clone Matching](https://arxiv.org/pdf/2003.05615.pdf), SCM detects code fragments similar to query.
SCM can detect type 1 (exact copy) and type 2 (renamed) clones.

**Tips**<br>
SCM is developed for my learning of **String Search Algorithm**. 
I can't guarantee it will work properly.
Currently, SCM supports the following algorithm.
- Rolling Hash

## Requirements
JDK 14

## Usage
1. Download latest ver. SCM from [here](https://github.com/T45K/SCM/releases).
2. `java -jar SimpleCloneMatcher.jar -i ... -q ...`

## Options
|Name|Option|Alias|Description|
|:-:|:-:|:-:|:-|
|Input direcotry|`-i`|`--input-dir`|Path of directory containing all source files from whitch you want to detect clones.<br>You must specify this or `-s` option.|
|Single source file|`-s`|`--source-file`|Path of source file from whitch you want to detect clones.<br>You must specify this or `-i` option|
|Query|`-q`|`--query`|Query. You must specify this option.|

## To be implemented
SCM will support the following algorithms.
- Suffix Array with Naive Array Construction
- Suffix Array with Doubling
- Suffix Array with SA-IS
- Longest Common Prefix Array

## License
MIT License
