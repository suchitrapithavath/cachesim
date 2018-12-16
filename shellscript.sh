#!/bin/sh
java cachemain -i gcc-10K.memtrace -cs 524288 -bs 16 -w 1
java cachemain -i gcc-10K.memtrace -cs 524288 -bs 16 -w 2
java cachemain -i gcc-10K.memtrace -cs 524288 -bs 16 -w 4
java cachemain -i gcc-10K.memtrace -cs 524288 -bs 16 -w 0
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 1
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 2
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 4
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 0
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 1 -vs 4
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 1 -vs 8
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 1 -vs 16
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 4 -vs 4
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 4 -vs 8
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 4 -vs 16
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 0 -vs 4
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 0 -vs 8
java cachemain -i gcc-1M.memtrace -cs 524288 -bs 16 -w 0 -vs 16

