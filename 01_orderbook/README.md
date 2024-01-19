# Performant Orderbooks

# Experiment Details

## Setup

- Application: minimal Spring Boot web app.
- Data points:
- Settings for exchange-core: 


## Running Spring Boot Web App

1. Start web app.
2. Generate synthetic data.
3. `curl` data to app.

```
curl ...
```

## Under the hood of exchange-core

In this minimal Spring Boot web app, we greatly reduce the code from exchange-gateway-rest. For example, we take the bare minimum required for consuming the output of the LMAX Disrupter's matching event handler.

Here's a lower-level explanation of what goes on in the event handler, that makes its way into the consumer provisioned in this Spring Boot web app. In exchange-core, MatchingEngineRouter's processMatchingCommand has this line: `cmd.resultCode = IOrderBook.processCommand(orderBook, cmd);`. This mutates the cmd object existing in the ring buffer. Subsequently, the consumer receives this command, and outputs the result code.

## Results

# Studying the use of Adaptive Radix Trees (ART) for order books

## Overview

## What goes on in code