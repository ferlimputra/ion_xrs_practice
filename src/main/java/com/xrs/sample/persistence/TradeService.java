package com.xrs.sample.persistence;

import java.util.Collection;
import javax.inject.Inject;
import com.iontrading.isf.committer.spi.CommitterListener;
import com.iontrading.isf.committer.spi.CommitterTransaction;
import com.iontrading.isf.committer.spi.CommitterTransactionFactory;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.committer.spi.Fetcher;
import com.iontrading.isf.committer.spi.TransactionOperationStrategy;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;
import com.iontrading.talk.api.exception.TalkFunctionException;

public class TradeService {

  private final CommitterTransactionFactory committer;
  private final Fetcher fetcher;

  @Inject
  public TradeService(final CommitterTransactionFactory committer, final Fetcher fetcher) {
    this.committer = committer;
    this.fetcher = fetcher;
  }

  @TalkFunction
  public AsyncResult<String> processTrade(@TalkParam(name = "TradeId") String tradeId,
      @TalkParam(name = "InstrumentId") String instrumentId, @TalkParam(name = "Qty") double qty,
      @TalkParam(name = "Value") double value, @TalkParam(name = "Verb") String verb) {

    final AsyncResultPromise<String> asyncResult = AsyncResults.create();

    final DomainTrade domainTrade = new DomainTrade();
    domainTrade.setTradeId(tradeId);
    domainTrade.setInstrumentId(instrumentId);
    domainTrade.setQty(qty);
    domainTrade.setValue(value);
    domainTrade.setVerb(verb);

    System.out.println("""
            Adding new Trade:
            - TradeId: %s
            - InstrumentId: %s
            - Qty: %f
            - Value: %f
            - Verb: %s
        """.formatted(tradeId, instrumentId, qty, value, verb));

    CommitterTransaction transaction = committer.create();

    try {
      transaction.addOrUpdate(domainTrade, TransactionOperationStrategy.CREATE_NEW_REVISION);
    } catch (Exception e) {
      throw new TalkFunctionException(e);
    }

    transaction.commit(new CommitterListener() {

      @Override
      public void onRollback(Collection<? extends DomainEntity> entities, Throwable reason) {
        System.out.println("Rolling back transaction. Reason: %s".formatted(reason.getMessage()));
        asyncResult.failure(reason);
      }

      @Override
      public void onPending(Collection<? extends DomainEntity> entities) {
      }

      @Override
      public void onCommit(Collection<? extends DomainEntity> entities) {
        System.out.println("Transaction Committed successfully.");
        asyncResult.success(domainTrade.getIdentifier().getId());
      }
    });
    return asyncResult;
  }

}
