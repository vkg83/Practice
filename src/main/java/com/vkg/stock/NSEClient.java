package com.vkg.stock;


import com.vkg.stock.client.FundDataProvider;
import com.vkg.stock.client.NSEJsoupClient;
import com.vkg.stock.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NSEClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NSEClient.class);

    private static final int MIN_ALLOWED_VOLUME = 100;
    private FundDataProvider dataProvider;

    public static void main(String[] args) throws Exception {
        NSEClient c = new NSEClient();
        c.dataProvider = new NSEJsoupClient();
        c.loadEtfInfo();
    }

    private void loadEtfInfo() {
        Predicate<Fund> vol = e -> e.getVolume() >= MIN_ALLOWED_VOLUME;
        Predicate<Fund> jwel = ((Predicate<Fund>) e -> e.getAssets().toUpperCase().contains("GOLD")).or(e -> e.getSymbol().contains("GOLD"))
                .or(e -> e.getAssets().toUpperCase().contains("SILVER")).or(e -> e.getSymbol().contains("SILVER"));
        Predicate<Fund> liquid = ((Predicate<Fund>) e1 -> e1.getAssets().toUpperCase().contains("LIQUID"))
                .or(e -> e.getSymbol().contains("LIQUID"));

        final List<Fund> allFunds = dataProvider.getAllFunds(FundType.ETF);

        List<Fund> etfs = allFunds.stream().filter(jwel.or(liquid).negate().and(vol)).collect(Collectors.toList());

        analyzeFunds(etfs);
        List<Fund> jwelFunds = allFunds.stream().filter(jwel.and(vol)).collect(Collectors.toList());
        analyzeFunds(jwelFunds);
    }

    private void analyzeFunds(List<Fund> funds) {
        funds.sort(Comparator.comparing(Fund::getSymbol));
        LOGGER.info("+---------------------------------------------------------------------------------------------------------+");
        LOGGER.info("|                                           Order By Name                                                 |");
        LOGGER.info("+---------------------------------------------------------------------------------------------------------+");
        for (int i = 0; i < funds.size(); i++) {
            Fund v = funds.get(i);
            LOGGER.info(String.format("| %3d | %-15s | %15d | %8.2f | %-50s |", i + 1, v.getSymbol(), v.getVolume(), v.getLastTradingPrice(), v.getAssets()));
        }
        LOGGER.info("+---------------------------------------------------------------------------------------------------------+");

        funds.sort(Comparator.comparing(Fund::getVolume).reversed());

        LOGGER.info("+---------------------------------------------------------------+");
        LOGGER.info("|                        Order by Volume                        |");
        LOGGER.info("+---------------------------------------------------------------+");
        List<FundAnalysis> fundAnalyses = new ArrayList<>();
        for (int i = 0; i < funds.size(); i++) {
            final Fund fund = funds.get(i);

            if (fund.getVolume() < MIN_ALLOWED_VOLUME) {
                LOGGER.info("Ignoring {} ETFs (Volume is less than {} minimum allowed)", funds.size() - i, MIN_ALLOWED_VOLUME);
                break;
            }

            List<FundHistory> fundHistory = dataProvider.getHistory(fund);
            FundAnalysis analysis = new FundAnalysis(fund, fundHistory);
            fundAnalyses.add(analysis);

            LOGGER.info(String.format("| %3d | Got History of: %-15s | %8.2f %% | %8.2f |", i + 1, fund.getSymbol(), analysis.getVolumeChangePercent(), fund.getLastTradingPrice()));
        }
        LOGGER.info("+---------------------------------------------------------------+");

        fundAnalyses.sort(Comparator.comparing(FundAnalysis::getVolumeChangePercent));
        final double meanVolume = fundAnalyses.get(fundAnalyses.size() / 2).getVolumeChangePercent();
        LOGGER.info(String.format("Average volume change: %8.2f %%", meanVolume));

        fundAnalyses.sort(Comparator.comparingDouble(FundAnalysis::getPriceChangePercent));
        LOGGER.info("+------------------------------------------------------------+");
        LOGGER.info("|                       Ranking Result                       |");
        LOGGER.info("+------------------------------------------------------------+");
        for (int i = 0; i < funds.size() && i < 10; i++) {
            FundAnalysis a = fundAnalyses.get(i);
            Fund v = a.getFund();
            LOGGER.info(String.format("| %3d | %-15s | %8.2f %% | %8.2f | %10s |", i + 1, v.getSymbol(), a.getPriceChangePercent(), v.getLastTradingPrice(), v.getActionDate()));
        }
        LOGGER.info("+------------------------------------------------------------+");
    }

}
