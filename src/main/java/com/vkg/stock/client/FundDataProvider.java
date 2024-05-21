package com.vkg.stock.client;

import com.vkg.stock.model.Fund;
import com.vkg.stock.model.FundHistory;
import com.vkg.stock.model.FundType;

import java.util.List;

public interface FundDataProvider {

    List<Fund> getAllFunds(FundType type);

    List<FundHistory> getHistory(Fund fund);
}
