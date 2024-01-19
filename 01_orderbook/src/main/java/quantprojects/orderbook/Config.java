package quantprojects.orderbook;

import exchange.core2.core.ExchangeCore;
import exchange.core2.core.common.config.ExchangeConfiguration;
import exchange.core2.core.common.config.InitialStateConfiguration;
import exchange.core2.core.common.config.LoggingConfiguration;
import exchange.core2.core.common.config.OrdersProcessingConfiguration;
import exchange.core2.core.common.config.PerformanceConfiguration;
import exchange.core2.core.common.config.ReportsQueriesConfiguration;
import exchange.core2.core.common.config.SerializationConfiguration;
import exchange.core2.core.IEventsHandler;
import exchange.core2.core.SimpleEventsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    SimpleEventsProcessor eventsProcessor = new SimpleEventsProcessor(new IEventsHandler() {
        @Override
        public void tradeEvent(TradeEvent tradeEvent) {
            System.out.println("Trade event: " + tradeEvent);
        }

        @Override
        public void reduceEvent(ReduceEvent reduceEvent) {
            System.out.println("Reduce event: " + reduceEvent);
        }

        @Override
        public void rejectEvent(RejectEvent rejectEvent) {
            System.out.println("Reject event: " + rejectEvent);
        }

        @Override
        public void commandResult(ApiCommandResult commandResult) {
            System.out.println("Command result: " + commandResult);
        }

        @Override
        public void orderBook(OrderBook orderBook) {
            System.out.println("OrderBook event: " + orderBook);
        }
    });

    @Bean
    public ExchangeCore exchangeCore() {
        final ExchangeConfiguration exchangeConfiguration = ExchangeConfiguration.defaultBuilder()
                .initStateCfg(InitialStateConfiguration.CLEAN_TEST)
                .performanceCfg(PerformanceConfiguration.throughputPerformanceBuilder().build()) // baseBuilder() uses
                                                                                                 // naive orderbook
                                                                                                 // impl; this uses
                                                                                                 // direct
                .reportsQueriesCfg(ReportsQueriesConfiguration.createStandardConfig())
                .ordersProcessingCfg(OrdersProcessingConfiguration.DEFAULT)
                .loggingCfg(LoggingConfiguration.DEFAULT)
                .serializationCfg(SerializationConfiguration.DEFAULT)
                .build();

        ExchangeCore core = ExchangeCore.builder()
                .resultsConsumer(eventsProcessor)
                .exchangeConfiguration(exchangeConfiguration)
                .build();

        core.startup();

        return core;
    }
}