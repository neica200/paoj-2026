package com.pao.laboratory11.exercise3;

import com.pao.laboratory11.exercise2.TransactionWithAccount;
import java.util.*;
import java.util.stream.Collector;

final class Snapshot {
    private final Map<String, Long> countByCountry;
    private final Map<String, Long> countByChannel;
    private final double totalAmount;
    private final List<TransactionWithAccount> topTransactions;

    public Snapshot(Map<String, Long> byCountry, Map<String, Long> byChannel, double total, List<TransactionWithAccount> top) {

        this.countByCountry = Collections.unmodifiableMap(new HashMap<>(byCountry));
        this.countByChannel = Collections.unmodifiableMap(new HashMap<>(byChannel));
        this.totalAmount = total;
        this.topTransactions = List.copyOf(top);
    }

    public Map<String, Long> getCountByCountry() { return countByCountry; }
    public Map<String, Long> getCountByChannel() { return countByChannel; }
    public double getTotalAmount() { return totalAmount; }
    public List<TransactionWithAccount> getTopTransactions() { return topTransactions; }
}

public class CustomCollectors {
    public static Collector<TransactionWithAccount, ?, Snapshot> toSnapshot(int topN) {


        class Agg {
            final Map<String, Long> countryMap = new HashMap<>();
            final Map<String, Long> channelMap = new HashMap<>();
            double total = 0.0;
            final List<TransactionWithAccount> txList = new ArrayList<>();


            void accumulate(TransactionWithAccount tx) {
                countryMap.put(tx.getCountry(), countryMap.getOrDefault(tx.getCountry(), 0L) + 1);
                channelMap.put(tx.getChannel(), channelMap.getOrDefault(tx.getChannel(), 0L) + 1);
                total += tx.getAmount();
                txList.add(tx);
            }

            Agg combine(Agg other) {
                other.countryMap.forEach((k, v) -> this.countryMap.put(k, this.countryMap.getOrDefault(k, 0L) + v));
                other.channelMap.forEach((k, v) -> this.channelMap.put(k, this.channelMap.getOrDefault(k, 0L) + v));
                this.total += other.total;
                this.txList.addAll(other.txList);
                return this;
            }

            Snapshot finish() {
                txList.sort(Comparator.comparingDouble(TransactionWithAccount::getAmount).reversed()
                        .thenComparingInt(TransactionWithAccount::getId));

                int limita = Math.min(topN, txList.size());
                List<TransactionWithAccount> topNList = txList.subList(0, limita);

                return new Snapshot(countryMap, channelMap, total, topNList);
            }
        }

        return Collector.of(
                Agg::new,
                Agg::accumulate,
                Agg::combine,
                Agg::finish
        );
    }

}
