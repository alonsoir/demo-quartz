package com.aironman.demoquartz.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "symbol",
        "rank",
        "price_usd",
        "price_btc",
        "24h_volume_usd",
        "market_cap_usd",
        "available_supply",
        "total_supply",
        "max_supply",
        "percent_change_1h",
        "percent_change_24h",
        "percent_change_7d",
        "last_updated",
        "price_eur",
        "24h_volume_eur",
        "market_cap_eur"
})
/**
 * "id": "ethereum",
 *         "name": "Ethereum",
 *         "symbol": "ETH",
 *         "rank": "2",
 *         "price_usd": "209.890698424",
 *         "price_btc": "0.02105754",
 *         "24h_volume_usd": "6620928931.25",
 *         "market_cap_usd": "22461522828.0",
 *         "available_supply": "107015332.0",
 *         "total_supply": "107015332.0",
 *         "max_supply": null,
 *         "percent_change_1h": "-1.37",
 *         "percent_change_24h": "-6.08",
 *         "percent_change_7d": "-7.18",
 *         "last_updated": "1563873202",
 *         "price_eur": "187.686571328",
 *         "24h_volume_eur": "5920507480.55",
 *         "market_cap_eur": "20085340789.0"
 * */

// IT is the same than BitCoinEuro, but it could change, so i will maintain the nature of Ethereum class.
//TODO refactor this class, BitCoinEuro and probably Ripple to a generic pojo.
public class Ethereum {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("rank")
    private String rank;
    @JsonProperty("price_usd")
    private String priceUsd;
    @JsonProperty("price_btc")
    private String priceBtc;
    @JsonProperty("24h_volume_usd")
    private String _24hVolumeUsd;
    @JsonProperty("market_cap_usd")
    private String marketCapUsd;
    @JsonProperty("available_supply")
    private String availableSupply;
    @JsonProperty("total_supply")
    private String totalSupply;
    @JsonProperty("max_supply")
    private String maxSupply;
    @JsonProperty("percent_change_1h")
    private String percentChange1h;
    @JsonProperty("percent_change_24h")
    private String percentChange24h;
    @JsonProperty("percent_change_7d")
    private String percentChange7d;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("price_eur")
    private String priceEur;
    @JsonProperty("24h_volume_eur")
    private String _24hVolumeEur;
    @JsonProperty("market_cap_eur")
    private String marketCapEur;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("rank")
    public String getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(String rank) {
        this.rank = rank;
    }

    @JsonProperty("price_usd")
    public String getPriceUsd() {
        return priceUsd;
    }

    @JsonProperty("price_usd")
    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    @JsonProperty("price_btc")
    public String getPriceBtc() {
        return priceBtc;
    }

    @JsonProperty("price_btc")
    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    @JsonProperty("24h_volume_usd")
    public String get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    @JsonProperty("24h_volume_usd")
    public void set24hVolumeUsd(String _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    @JsonProperty("market_cap_usd")
    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    @JsonProperty("market_cap_usd")
    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    @JsonProperty("available_supply")
    public String getAvailableSupply() {
        return availableSupply;
    }

    @JsonProperty("available_supply")
    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    @JsonProperty("total_supply")
    public String getTotalSupply() {
        return totalSupply;
    }

    @JsonProperty("total_supply")
    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    @JsonProperty("max_supply")
    public String getMaxSupply() {
        return maxSupply;
    }

    @JsonProperty("max_supply")
    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    @JsonProperty("percent_change_1h")
    public String getPercentChange1h() {
        return percentChange1h;
    }

    @JsonProperty("percent_change_1h")
    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    @JsonProperty("percent_change_24h")
    public String getPercentChange24h() {
        return percentChange24h;
    }

    @JsonProperty("percent_change_24h")
    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    @JsonProperty("percent_change_7d")
    public String getPercentChange7d() {
        return percentChange7d;
    }

    @JsonProperty("percent_change_7d")
    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    @JsonProperty("last_updated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("last_updated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("price_eur")
    public String getPriceEur() {
        return priceEur;
    }

    @JsonProperty("price_eur")
    public void setPriceEur(String priceEur) {
        this.priceEur = priceEur;
    }

    @JsonProperty("24h_volume_eur")
    public String get24hVolumeEur() {
        return _24hVolumeEur;
    }

    @JsonProperty("24h_volume_eur")
    public void set24hVolumeEur(String _24hVolumeEur) {
        this._24hVolumeEur = _24hVolumeEur;
    }

    @JsonProperty("market_cap_eur")
    public String getMarketCapEur() {
        return marketCapEur;
    }

    @JsonProperty("market_cap_eur")
    public void setMarketCapEur(String marketCapEur) {
        this.marketCapEur = marketCapEur;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("\n")
                .append("name", name).append("\n")
                .append("symbol", symbol).append("\n")
                .append("rank", rank).append("\n")
                .append("priceUsd", priceUsd).append("\n")
                .append("priceBtc", priceBtc)
                .append("_24hVolumeUsd", _24hVolumeUsd).append("\n")
                .append("marketCapUsd", marketCapUsd).append("\n")
                .append("availableSupply", availableSupply).append("\n")
                .append("totalSupply", totalSupply).append("\n")
                .append("maxSupply", maxSupply).append("\n")
                .append("percentChange1h", percentChange1h).append("\n")
                .append("percentChange24h", percentChange24h).append("\n")
                .append("percentChange7d", percentChange7d).append("\n")
                .append("lastUpdated", lastUpdated).append("\n")
                .append("priceEur", priceEur).append("\n")
                .append("_24hVolumeEur", _24hVolumeEur).append("\n")
                .append("marketCapEur", marketCapEur).append("\n")
                .append("additionalProperties", additionalProperties).toString();
    }

}

