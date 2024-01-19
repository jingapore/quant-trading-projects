# Quant trading projects

# Intro
These projects studyquant trading systems, from orderbooks to protocols like SBE.

# 1_orderbook
Comparing empirical performance gains from exchange-core's direct orderbook implementation (versus naive implementation).

The theoretic gains come from:
1. Object pooling; and
2. Use of Adaptive Radix Trees (ART) instead of vanilla trees.

Broadly, direct orderbook implementation has XX higher throughput than naive implementation.