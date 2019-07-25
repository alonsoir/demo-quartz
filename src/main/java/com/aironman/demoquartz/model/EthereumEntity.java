package com.aironman.demoquartz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

// TODO Probably i will refactor this entity to a generic pojo to maintain full data in the same table...
@Entity
@Table(name = "EthereumEntity")
public class EthereumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idETHEntity", updatable = false, nullable = false)
    private Long idETHEntity;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "id", updatable = true, nullable = false)
    private String id;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "name", updatable = true, nullable = false)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "symbol", updatable = true, nullable = false)
    private String symbol;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "rank", updatable = true, nullable = false)
    private String rank;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "priceUsd", updatable = true, nullable = false)
    private String priceUsd;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "priceBtc", updatable = true, nullable = false)
    private String priceBtc;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "_24hVolumeUsd", updatable = true, nullable = false)
    private String _24hVolumeUsd;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "marketCapUsd", updatable = true, nullable = false)
    private String marketCapUsd;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "availableSupply", updatable = true, nullable = false)
    private String availableSupply;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "totalSupply", updatable = true, nullable = false)
    private String totalSupply;

    //@NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "maxSupply", updatable = true, nullable = true)
    private String maxSupply;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "percentChange1h", updatable = true, nullable = false)
    private String percentChange1h;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "percentChange24h", updatable = true, nullable = false)
    private String percentChange24h;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "percentChange7d", updatable = true, nullable = false)
    private String percentChange7d;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "lastUpdated", updatable = true, nullable = false)
    private String lastUpdated;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "priceEur", updatable = true, nullable = false)
    private String priceEur;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "_24hVolumeEur", updatable = true, nullable = false)
    private String _24hVolumeEur;

    @NotEmpty
    @Size(min = 1, max = 15)
    @Column(name = "marketCapEur", updatable = true, nullable = false)
    private String marketCapEur;

    public EthereumEntity(){
        super();
    }

    public EthereumEntity(Long idETHEntity, String id, String name, String symbol, String rank, String priceUsd, String priceBtc, String _24hVolumeUsd, String marketCapUsd, String availableSupply, String totalSupply, String maxSupply, String percentChange1h, String percentChange24h, String percentChange7d, String lastUpdated, String priceEur, String _24hVolumeEur, String marketCapEur) {
        this.idETHEntity = idETHEntity;
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this._24hVolumeUsd = _24hVolumeUsd;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
        this.priceEur = priceEur;
        this._24hVolumeEur = _24hVolumeEur;
        this.marketCapEur = marketCapEur;
    }

    public Long getIdETHEntity() {
        return idETHEntity;
    }

    public void setIdETHEntity(Long idETHEntity) {
        this.idETHEntity = idETHEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String get_24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set_24hVolumeUsd(String _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(String priceEur) {
        this.priceEur = priceEur;
    }

    public String get_24hVolumeEur() {
        return _24hVolumeEur;
    }

    public void set_24hVolumeEur(String _24hVolumeEur) {
        this._24hVolumeEur = _24hVolumeEur;
    }

    public String getMarketCapEur() {
        return marketCapEur;
    }

    public void setMarketCapEur(String marketCapEur) {
        this.marketCapEur = marketCapEur;
    }

    @Override
    public String toString() {
        return "EthereumEntity [idETHEntity=" + idETHEntity + ", id=" + id + ", name=" + name + ", symbol=" + symbol
                + ", rank=" + rank + ", priceUsd=" + priceUsd + ", priceBtc=" + priceBtc + ", _24hVolumeUsd="
                + _24hVolumeUsd + ", marketCapUsd=" + marketCapUsd + ", availableSupply=" + availableSupply
                + ", totalSupply=" + totalSupply + ", maxSupply=" + maxSupply + ", percentChange1h=" + percentChange1h
                + ", percentChange24h=" + percentChange24h + ", percentChange7d=" + percentChange7d + ", lastUpdated="
                + lastUpdated + ", priceEur=" + priceEur + ", _24hVolumeEur=" + _24hVolumeEur + ", marketCapEur="
                + marketCapEur + "]";
    }

}
